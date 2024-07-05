package com.example.data.cache

import com.example.data.dto.ApiResponse
import com.example.data.local.LocalDataSource
import com.example.data.services.RemoteDataSource

abstract class CachePolicyRepository<T>(
    private val localDataSource: LocalDataSource<String, CacheEntry<T>>,
    private val remoteDataSource: RemoteDataSource<T>
) {

    suspend fun fetch(url: String, cachePolicy: CachePolicy): T? {
        return when (cachePolicy.type) {
            CachePolicy.Type.NEVER -> remoteDataSource.fetch(url)
            CachePolicy.Type.ALWAYS -> {
                localDataSource.get(url)?.value ?: fetchAndCache(url)
            }
            CachePolicy.Type.CLEAR -> {
                localDataSource.get(url)?.value.also {
                    localDataSource.remove(url)
                }
            }
            CachePolicy.Type.REFRESH -> fetchAndCache(url)
            CachePolicy.Type.EXPIRES -> {
                localDataSource.get(url)?.let {
                    if( (it.createdAt + cachePolicy.expires) > System.currentTimeMillis()) {
                        it.value
                    } else {
                        fetchAndCache(url)
                    }
                } ?: fetchAndCache(url)
            }
            else -> null
        }
    }

    private suspend fun fetchAndCache(url:String): T {
        return remoteDataSource.fetch(url).also {
            localDataSource.set(url, CacheEntry(key = url, value = it))
        }
    }

    suspend fun put(url: String, data: T, cachePolicy: CachePolicy): T {
        return when (cachePolicy.type) {
            CachePolicy.Type.NEVER -> remoteDataSource.put(url, data)
            CachePolicy.Type.ALWAYS -> {
                val updatedData = remoteDataSource.put(url, data)
                localDataSource.set(url, CacheEntry(key = url, value = updatedData))
                updatedData
            }
            CachePolicy.Type.CLEAR -> {
                localDataSource.remove(url)
                remoteDataSource.put(url, data)
            }
            CachePolicy.Type.REFRESH -> {
                val updatedData = remoteDataSource.put(url, data)
                localDataSource.set(url, CacheEntry(key = url, value = updatedData))
                updatedData
            }
            CachePolicy.Type.EXPIRES -> {
                localDataSource.get(url)?.let {
                    if ((it.createdAt + cachePolicy.expires) > System.currentTimeMillis()) {
                        it.value
                    } else {
                        val updatedData = remoteDataSource.put(url, data)
                        localDataSource.set(url, CacheEntry(key = url, value = updatedData))
                        updatedData
                    }
                } ?: remoteDataSource.put(url, data)
            }
            else -> remoteDataSource.put(url, data)
        }
    }

    suspend fun post(url: String, data: T, cachePolicy: CachePolicy): ApiResponse {
        return when (cachePolicy.type) {
            CachePolicy.Type.NEVER -> remoteDataSource.post(url, data)
            CachePolicy.Type.ALWAYS -> {
                val createdData = remoteDataSource.post(url, data)
                localDataSource.set(url, CacheEntry(key = url, value = data))
                createdData
            }
            CachePolicy.Type.CLEAR -> {
                localDataSource.remove(url)
                remoteDataSource.post(url, data)
            }
            CachePolicy.Type.REFRESH -> {
                val createdData = remoteDataSource.post(url, data)
                localDataSource.set(url, CacheEntry(key = url, value = data))
                createdData
            }
            else -> remoteDataSource.post(url, data)
        }
    }

    suspend fun delete(url : String) : Boolean {
        return remoteDataSource.delete(url).also { success ->
            if (success) {
                localDataSource.remove(url)
            }
        }
    }

    suspend fun getAll() : ApiResponse {
        return remoteDataSource.fetchAll()
    }
}

data class CacheEntry<T>(
    val key: String,
    val value: T,
    val createdAt: Long = System.currentTimeMillis()
)


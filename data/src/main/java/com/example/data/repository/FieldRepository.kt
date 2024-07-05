package com.example.data.repository

import com.example.data.cache.CacheEntry
import com.example.data.cache.CachePolicy
import com.example.data.cache.CachePolicyRepository
import com.example.data.dto.ApiResponse
import com.example.data.dto.FieldDto
import com.example.data.local.LocalDataSource
import com.example.data.services.RemoteDataSource
import java.lang.reflect.Field

class FieldRepository (private val fieldLocalDataSource : LocalDataSource<String, CacheEntry<FieldDto>>,
                       private val fieldRemoteDataSource : RemoteDataSource<FieldDto>) :
        CachePolicyRepository<FieldDto>(
            localDataSource = fieldLocalDataSource,
            remoteDataSource = fieldRemoteDataSource
        ) {


    suspend fun getField(url : String, cachePolicy: CachePolicy): FieldDto? {
        return fetch(url, cachePolicy)

    }

    suspend fun getAllFields() : ApiResponse {
        return getAll()
    }

    suspend fun putField(url: String, data : FieldDto, cachePolicy: CachePolicy) : FieldDto {
        return put(url, data, cachePolicy)
    }

    suspend fun postField(key: String, data : FieldDto, cachePolicy: CachePolicy) : ApiResponse {
        return post(key, data, cachePolicy)
    }

    suspend fun deleteField(key: String) : Boolean {
        return delete(key)
    }




        }
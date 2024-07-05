package com.example.data.local

import com.example.data.cache.CacheEntry
import com.example.data.dto.FieldDto


interface LocalDataSource<Key : Any, T> {
    fun getAll() : MutableMap<String, T>
    fun get(url : String) : T?
    fun set(url : String, value: T)
    fun remove(url : String)
    fun clear()
}

class LocalFieldSource : LocalDataSource<String, CacheEntry<FieldDto>> {
    private val fields = mutableMapOf<String, CacheEntry<FieldDto>>()

    override fun getAll(): MutableMap<String, CacheEntry<FieldDto>> {
        return fields
    }
    override fun get(url : String): CacheEntry<FieldDto>? {
        return fields[url]
    }

    override fun set(url : String, value: CacheEntry<FieldDto>) {
        fields[url] = value
    }

    override fun remove(url : String) {
        fields.remove(url)
    }

    override fun clear() {
        fields.clear()
    }
}
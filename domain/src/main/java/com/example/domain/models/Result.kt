package com.example.domain.models

sealed class Result{
    data class Success<T>(val value: T): Result()

    data class ErrorNetwork(val value: Unit): Result()//data class errora

    data class Error(val value: Throwable): Result()//custom err
}
package com.example.catcraft.arch

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Error(val msg: String) : Resource<Nothing>()
}
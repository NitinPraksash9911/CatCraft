package com.nitin.networkerrorhandler.datasource.model

sealed class DataStatus {
    object HTTPError : DataStatus()
    object NetworkError : DataStatus()
    object GotException : DataStatus()
    object InvalidData : DataStatus()
    object ServerError : DataStatus()
}

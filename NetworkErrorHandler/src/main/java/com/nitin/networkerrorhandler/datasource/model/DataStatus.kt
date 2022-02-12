package com.nitin.networkerrorhandler.datasource.model

sealed class DataStatus {
    object HTTPError : DataStatus()  //error from
    object GotException : DataStatus()
    object InvalidData : DataStatus()
}

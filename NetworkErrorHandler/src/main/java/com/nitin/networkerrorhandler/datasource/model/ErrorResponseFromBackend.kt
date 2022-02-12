package com.nitin.networkerrorhandler.datasource.model

import com.google.gson.annotations.SerializedName


//graphql response
data class ErrorResponseFromBackend(
    @SerializedName("errors") var errors: ArrayList<Error> = arrayListOf(),
    @SerializedName("data") var data: Data? = Data()
)

data class Error(
    @SerializedName("err_msg") var errMsg: String? = null,
    @SerializedName("err_code") var errCode: Int? = null,
    @SerializedName("err_str") var errStr: String? = null,
    @SerializedName("err_image") var errImage: String? = null,
    @SerializedName("err_visible") var errVisible: Boolean? = null,
    @SerializedName("err_type") var errType: String? = null
)


data class Data(
    @SerializedName("allStories") var allStories: String? = null
)

package com.jumpingminds.networkrequesthandler.datasource.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**

@author: Nitin Prakash
@date: 12-Feb-2022

This class represent the Apollo Error.nonStandardFields [com.apollographql.apollo3.api.Error.nonStandardFields] which we can get from the server
while executing graphQl query

 */
data class GraphQlError(
    @SerializedName("err_msg") var errorMsg: String? = null,
    @SerializedName("err_title") var errorTitle: String? = null,
    @SerializedName("err_image") var errorImageUrl: String? = null,
    @SerializedName("err_visible") var errorVisibility: Boolean? = true,
    @SerializedName("err_type") var errorType: String? = "snack"
) {
    companion object {

        fun from(gson: Gson, nonStandardFields: Map<String, Any?>?): GraphQlError {
            val jsonString = gson.toJson(nonStandardFields).toString()
            return try {
                gson.fromJson(
                    jsonString,
                    GraphQlError::class.java
                )
            } catch (e: Exception) {
                GraphQlError()
            }
        }
    }
}



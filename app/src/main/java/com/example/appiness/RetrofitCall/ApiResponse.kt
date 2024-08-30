package com.example.appiness.RetrofitCall

sealed class ApiResponse<out T> {


    data class successReponse <out T> ( val data : T) : ApiResponse<T>()

    data class failureResponse <T> (val message : T) : ApiResponse<Nothing>()

    data object isLoading : ApiResponse<Nothing>()



}
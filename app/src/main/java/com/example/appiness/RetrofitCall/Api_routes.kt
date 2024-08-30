package com.example.appiness.RetrofitCall

import ProductsResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api_routes {

    @GET("test/products.php")
    fun getProducts() : Call<ProductsResponse>
}
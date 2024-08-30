package com.example.appiness.RetrofitCall

import com.example.appiness.Utilities.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  ApiCall {

    val api : Api_routes by lazy {

        Retrofit.Builder().baseUrl(Utils.baseurl)
            .addConverterFactory(GsonConverterFactory.create()).
                build().create(Api_routes::class.java)

    }

}
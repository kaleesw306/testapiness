package com.example.appiness.ViewModel

import Product
import ProductsResponse
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appiness.RetrofitCall.ApiCall
import com.example.appiness.RetrofitCall.ApiResponse
import com.example.appiness.Database.Application_Database
import com.example.appiness.Database.ProductDao
import com.example.appiness.Database.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID

class ProductListViewModel  : ViewModel(){

    private val product_data = MutableLiveData<ApiResponse<List<Product>>>()
    val data: LiveData<ApiResponse<List<Product>>> get() = product_data
    lateinit var productDao: ProductDao

    fun getProducts(context: Context) {
        productDao = Application_Database.getDatabase(context).productDao()
        viewModelScope.launch(Dispatchers.IO) {
            val productsDatabase = productDao.getproducts()
            Log.e("Database value", productsDatabase.toString())


            if (productsDatabase.isEmpty()) {
                Log.e("Database Status", "database is empty")
                product_data.postValue(ApiResponse.isLoading)
                getValues()
            } else {
                val products = productsDatabase.map { productEntity ->
                    Product(
                        name = productEntity.name,
                        description = productEntity.description,
                        availableLanguages = productEntity.availableLanguages,
                        sampleReports = productEntity.sampleReports,
                        pages = productEntity.pages,
                        authentic = productEntity.authentic,
                        remedies = productEntity.remedies,
                        vedic = productEntity.vedic,
                        price = productEntity.price,
                        discount = productEntity.discount,
                        appDiscount = productEntity.appDiscount,
                        couponDiscount = productEntity.couponDiscount,
                        imagePath = productEntity.imagePath,
                        soldcount = productEntity.soldCount,
                        avg = productEntity.avg,
                        pagesintext = productEntity.pagesInText,
                        report_type = productEntity.reportType
                    )
                }
                product_data.postValue(ApiResponse.successReponse(products))
                getValues()
            }
        }
    }

    fun getValues() {
        ApiCall.api.getProducts().enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { productsResponse ->
                        val products = productsResponse.products?.values?.toList() ?: emptyList()
                        product_data.value = ApiResponse.successReponse(products)

                        val productEntities = products.map {
                            ProductEntity(
                                id = UUID.randomUUID().toString(),
                                name = it.name!!,
                                description = it.description!!,
                                availableLanguages = it.availableLanguages,
                                sampleReports = it.sampleReports,
                                pages = it.pages,
                                pagesInText = it.pagesintext!!,
                                reportType = it.report_type!!,
                                authentic = it.authentic!!,
                                remedies = it.remedies!!,
                                vedic = it.vedic!!,
                                price = it.price,
                                discount = it.discount,
                                appDiscount = it.appDiscount,
                                couponDiscount = it.couponDiscount,
                                imagePath = it.imagePath,
                                soldCount = it.soldcount!!,
                                avg = it.avg!!
                            )
                        }

                        viewModelScope.launch(Dispatchers.IO) {
                            productDao.clearProducts()
                            productDao.insertProduct(productEntities)
                        }
                    } ?: run {
                        product_data.value = ApiResponse.failureResponse("Product data is empty")
                    }
                } else {
                    product_data.value = ApiResponse.failureResponse("Something went wrong")
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Log.e("Error Response", t.message.toString())
                product_data.value = ApiResponse.failureResponse(t.message.toString())
            }
        })
    }

}
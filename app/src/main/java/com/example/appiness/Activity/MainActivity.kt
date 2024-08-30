package com.example.appiness.Activity

import Product
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appiness.Adapter.Productadapter
import com.example.appiness.RetrofitCall.ApiResponse
import com.example.appiness.Utilities.onClickListner
import com.example.appiness.ViewModel.ProductListViewModel
import com.example.appiness.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,onClickListner{



    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var productListViewModel: ProductListViewModel

    lateinit var productAdapter : Productadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        productListViewModel = ViewModelProvider(this)[ProductListViewModel::class.java]
        productListViewModel.getProducts(context = this@MainActivity)
        observeData()
    }

    private fun observeData() {
        productListViewModel.data.observe(this) { apiResponse ->
            when (apiResponse) {
                is ApiResponse.failureResponse<*> -> {
                    activityMainBinding.loaderId.visibility = View.GONE
                    activityMainBinding.noData.visibility = View.VISIBLE
                    activityMainBinding.listRecylerview.visibility = View.GONE
                }

                is ApiResponse.isLoading -> {
                    activityMainBinding.loaderId.visibility = View.VISIBLE
                    activityMainBinding.listRecylerview.visibility = View.GONE
                    activityMainBinding.noData.visibility = View.GONE
                }

                is ApiResponse.successReponse -> {
                    activityMainBinding.listRecylerview.visibility = View.VISIBLE
                    activityMainBinding.loaderId.visibility = View.GONE
                    activityMainBinding.noData.visibility = View.GONE

                    val productList: List<Product> = apiResponse.data
                    productAdapter = Productadapter(productList,this)
                    activityMainBinding.listRecylerview.adapter = productAdapter
                    activityMainBinding.listRecylerview.setHasFixedSize(true)
                    activityMainBinding.listRecylerview.layoutManager = LinearLayoutManager(
                        this@MainActivity, LinearLayoutManager.VERTICAL, false
                    )
                }
            }
        }
    }

    override fun onClick(position: Int, product: Product) {
        super.onClick(position, product)

        Log.e("Values", product.toString())
        val intent = Intent(this, ProductDetailActivity::class.java).apply {
            putExtra("name", product.name.toString())
            putExtra("price", product.price.toString())
            putExtra("squareImg", product.imagePath!!["square"])
            putExtra("bannerImg", product.imagePath!!["wide"])
            putExtra("desc", product.description.toString())
            putExtra("soldcount", product.soldcount.toString())
        }
        startActivity(intent)
    }



}



//https://apps.clickastro.com/test/products.php
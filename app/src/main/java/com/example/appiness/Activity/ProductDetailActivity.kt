package com.example.appiness.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.appiness.R
import com.example.appiness.databinding.DetailActivityBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var detailActivityBinding: DetailActivityBinding

    private lateinit var name:String
    private lateinit var desc:String
    private lateinit var price:String
    private lateinit var squareImg:String
    private lateinit var bannerImg:String
    private lateinit var soldCount:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivityBinding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)
        intent.data.apply {
             name = intent.getStringExtra("name").toString()
             desc = intent.getStringExtra("desc").toString()
             price = intent.getStringExtra("price").toString()
             squareImg = intent.getStringExtra("squareImg").toString()
             bannerImg = intent.getStringExtra("bannerImg").toString()
             soldCount = intent.getStringExtra("soldcount").toString()
        }
        appBar()

        detailActivityBinding.nameId.text = name
        detailActivityBinding.priceId.text ="Price : $price"
        detailActivityBinding.descId.text = desc
        detailActivityBinding.soldCount.text = "$soldCount recently customers bought"

        Glide.with(baseContext).applyDefaultRequestOptions(
            RequestOptions.placeholderOf(R.drawable.placeholder)
        )
            .load(squareImg)
            .into(detailActivityBinding.squareImg)
        Glide.with(baseContext).applyDefaultRequestOptions(
            RequestOptions.placeholderOf(R.drawable.placeholder)
        )
            .load(bannerImg)
            .into(detailActivityBinding.wideImg)

    }

    fun appBar(){
        detailActivityBinding.proName.title = name
        detailActivityBinding.proName.setNavigationIcon(resources.getDrawable(R.drawable.baseline_arrow_back_24,theme))
        detailActivityBinding.proName.setNavigationOnClickListener {
           onBackPressedDispatcher.onBackPressed()
        }
    }
}
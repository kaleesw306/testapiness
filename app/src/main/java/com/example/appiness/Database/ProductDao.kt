package com.example.appiness.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {


    @Query("SELECT * FROM products")
     fun getproducts(): List<ProductEntity>

    @Insert
     fun insertProduct(product: List<ProductEntity>)

    @Query("DELETE FROM products")
     fun clearProducts()
}
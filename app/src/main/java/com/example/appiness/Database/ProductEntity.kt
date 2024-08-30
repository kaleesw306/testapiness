package com.example.appiness.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    @TypeConverters(Converters::class) val availableLanguages: List<String>?,
    @TypeConverters(Converters::class) val sampleReports: Map<String, String>?,
    val pages: Int,
    val pagesInText: String,
    val reportType: String,
    val authentic: String,
    val remedies: String,
    val vedic: String,
    val price: Int,
    val discount: Int,
    val appDiscount: Int,
    val couponDiscount: Int,
    @TypeConverters(Converters::class) val imagePath: Map<String, String>?,
    val soldCount: String,
    val avg: Double,
)
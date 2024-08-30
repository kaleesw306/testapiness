package com.example.appiness.Database


import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromListString(list: List<String>?): String? {
        return list?.let {
            Gson().toJson(it)
        }
    }

    @TypeConverter
    fun toListString(listString: String?): List<String>? {
        return listString?.let {
            Gson().fromJson(it, Array<String>::class.java).toList()
        }
    }

    @TypeConverter
    fun fromMapString(map: Map<String, String>?): String? {
        return map?.let {
            Gson().toJson(it)
        }
    }

    @TypeConverter
    fun toMapString(mapString: String?): Map<String, String>? {
        return mapString?.let {
            Gson().fromJson(it, Map::class.java) as Map<String, String>
        }
    }
}
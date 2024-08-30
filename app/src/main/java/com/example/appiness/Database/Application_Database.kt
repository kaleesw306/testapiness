package com.example.appiness.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [ProductEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class Application_Database : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var instance_db: Application_Database? = null

        fun getDatabase(context: Context): Application_Database {
            return instance_db ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Application_Database::class.java,
                    "appiness_database"
                ).build()
                instance_db = instance
                instance
            }
        }
    }
}
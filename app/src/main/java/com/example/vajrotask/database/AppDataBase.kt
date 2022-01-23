package com.example.vajrotask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vajrotask.database.dao.DataModelDao
import com.example.vajrotask.database.model.MyData

@Database(
    entities = [MyData::class],
    version = 2,
    exportSchema = false
)

//@TypeConverters(ListConverters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract val dataModelDao: DataModelDao

    companion object {

        val DB_NAME = "vajro_db"
        private var INSTANCE: AppDataBase? = null
        private val sLock = Any()

        fun getInstance(context: Context): AppDataBase? {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java, DB_NAME
                    )
                        .addMigrations()
                        .allowMainThreadQueries()
                        .build()
                }
                return INSTANCE
            }
        }
    }

}
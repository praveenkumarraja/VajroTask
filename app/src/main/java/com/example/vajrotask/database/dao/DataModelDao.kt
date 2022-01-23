package com.example.vajrotask.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vajrotask.database.model.MyData


@Dao
interface DataModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAvg(sectionItem: List<MyData>)

    @Query("SELECT * FROM MydataTable")
    fun getAll(): List<MyData>

    @Query("UPDATE MydataTable SET inputQuantity=:quantity WHERE id = :id")
    fun update(quantity: Int, id: Int)

    @Query("SELECT * FROM MydataTable WHERE id = :id")
    fun getQuantity(id: Int):MyData

}

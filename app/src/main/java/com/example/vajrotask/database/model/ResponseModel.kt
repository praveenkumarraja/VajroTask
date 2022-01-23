package com.example.vajrotask.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("products")
    var products: List<MyData>? = null
)

@Entity(tableName = "MydataTable")
data class MyData(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int? = 0,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    var image: String? = null,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    var price: String? = null,

    @ColumnInfo(name = "inputQuantity")
    var inputQuantity: Int = 0
)

//
//class ListConverters {
//
//    @TypeConverter
//    fun listToJson(value: List<Product>?) = Gson().toJson(value)
//
//    @TypeConverter
//    fun jsonToList(value: String) = Gson().fromJson(value, Array<Product>::class.java).toList()
//}
package com.example.vajrotask.network
import com.example.vajrotask.database.model.MyData
import com.example.vajrotask.database.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("5def7b172f000063008e0aa2")
    fun getDataFromGitHubRepo(): Call<Product>

}
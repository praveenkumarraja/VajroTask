package com.example.vajrotask.ui.dashboard

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vajrotask.database.AppDataBase
import com.example.vajrotask.database.model.MyData
import com.example.vajrotask.database.model.Product
import com.example.vajrotask.network.ApiInterface
import com.example.vajrotask.network.RetroFitCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {

    val isDataCallCompleted: MutableLiveData <List<MyData>>? = MutableLiveData <List<MyData>>()

    fun getApiCall(context: Context, db: AppDataBase?) {
        RetroFitCall.retroFitCall()
        val service = RetroFitCall.retrofit.create(ApiInterface::class.java)
        val call = service.getDataFromGitHubRepo()
        call.enqueue(object : Callback<Product> {
            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
                if (response.code() == 200) {
                    println("response-->"+response.body())

                    var auditList: List<MyData>? = response.body()?.products
                    if (auditList != null) {
                        db?.dataModelDao?.addAvg(auditList)
                    }
                    isDataCallCompleted?.postValue(db?.dataModelDao?.getAll())
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(context, "failed", Toast.LENGTH_LONG).show()
            }
        })
    }

}
package com.example.vajrotask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vajrotask.R
import com.example.vajrotask.database.AppDataBase
import com.example.vajrotask.database.model.MyData
import kotlinx.android.synthetic.main.list_recycler.view.*


class HomeDataAdapter(
    val context: Context,
    private val chaptersList: List<MyData>,
    val db: AppDataBase,
) :
    RecyclerView.Adapter<HomeDataAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_recycler, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = chaptersList?.get(position)

        holder.itemView.plusBtn.visibility = View.GONE
         holder.itemView.quantityItems.text =
                db.dataModelDao.getAll()[position].inputQuantity.toString()
            println("DB Check-->" + data?.id?.let { it1 -> db.dataModelDao.getAll()[position] })
            Glide.with(context)
                .load(data?.image)
                .into(holder.itemView.imgData)
            holder.itemView.nameTxt.text = "Product Name: " + data?.name
            holder.itemView.priceTxt.setText("Price: " + data?.price)
            holder.itemView.minusBtn.text = "Quantity"

            holder.itemView.quantityItems.text = data?.inputQuantity.toString()

    }

    override fun getItemCount(): Int {
        return chaptersList.size
    }
}
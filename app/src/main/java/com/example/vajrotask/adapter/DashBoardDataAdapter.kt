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


class DashBoardDataAdapter(
    val context: Context,
    private val chaptersList: List<MyData>,
    val db: AppDataBase,
) :
    RecyclerView.Adapter<DashBoardDataAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_recycler, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = chaptersList?.get(position)
        //By using glide, we can avoid using bitmap cache holders,
        //As Glide default behaviour is the same as caching once loaded all the images
        //No need to load again
        Glide.with(context)
            .load(data?.image)
            .into(holder.itemView.imgData)
        holder.itemView.nameTxt.text = data?.name
        holder.itemView.priceTxt.setText("Price: " + data?.price)
        holder.itemView.quantityItems.text = data?.inputQuantity.toString()

        holder.itemView.plusBtn.setOnClickListener {

            data?.id?.let { it1 ->
                db.dataModelDao.update(
                    db.dataModelDao.getQuantity(data.id!!).inputQuantity + 1,
                    it1
                )
            }
            holder.itemView.quantityItems.text =
                db.dataModelDao.getAll()[position].inputQuantity.toString()
            println("DB Check-->" + data?.id?.let { it1 -> db.dataModelDao.getAll()[position] })
        }
        holder.itemView.minusBtn.setOnClickListener {
            if (db.dataModelDao.getQuantity(data.id!!).inputQuantity > 0) {
                data?.id?.let { it1 ->
                    db.dataModelDao.update(
                        db.dataModelDao.getQuantity(data.id!!).inputQuantity - 1,
                        it1
                    )
                }
                holder.itemView.quantityItems.text =
                    db.dataModelDao.getAll()[position].inputQuantity.toString()
                println("DB Check-->" + data?.id?.let { it1 -> db.dataModelDao.getAll()[position] })
            }
        }
    }

    override fun getItemCount(): Int {
        return chaptersList?.size!!
    }
}
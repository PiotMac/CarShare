package com.example.carshare.ui.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carshare.Cars
import com.example.carshare.R

import java.util.ArrayList

class OrdersAdapter(private val carsList : ArrayList<Cars>) : RecyclerView.Adapter<OrdersAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListtner

    interface onItemClickListtner {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListtner) {

        mListener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_sample_my_cars,
            parent, false
        )
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = carsList[position]
        holder.car_name.text = currentItem.car_name
        holder.car_class.text = currentItem.car_class
        holder.car_rating.text = currentItem.car_rating.toString()
    }

    override fun getItemCount(): Int {
        return carsList.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListtner) :
        RecyclerView.ViewHolder(itemView) {
        val car_name: TextView = itemView.findViewById(R.id.textNameCar)
        val car_class: TextView = itemView.findViewById(R.id.textClassCar)
        val car_rating: TextView = itemView.findViewById(R.id.textRating)

        init {

            itemView.setOnClickListener {

                listener.onItemClick(adapterPosition)

            }

        }

    }
}
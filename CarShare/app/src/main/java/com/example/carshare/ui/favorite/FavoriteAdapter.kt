package com.example.carshare.ui.favorite


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView
import com.example.carshare.Cars
import com.example.carshare.R

class FavoriteAdapter(private val carsList :ArrayList<Cars>) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>(){

    private lateinit var mListener : onItemClickListtner

    interface onItemClickListtner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListtner){

        mListener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_sample_car_info,
            parent,false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = carsList[position]
        holder.car_name.text = currentItem.car_name
        holder.car_class.text = currentItem.car_class
        holder.car_gearbox.text = currentItem.car_gearbox
        holder.car_fuel.text = currentItem.car_fuel
        holder.car_address.text = currentItem.car_address
        holder.car_rating.text = currentItem.car_rating.toString()
        holder.car_passengers.text = currentItem.car_passengers.toString() + " passenger(s)"
        holder.car_cost.text = currentItem.car_cost.toString() + " PLN/day"
        holder.car_bags.text = "Small bags:" + currentItem.car_bags.toString()
    }

    override fun getItemCount(): Int {
        return carsList.size
    }
    class MyViewHolder(itemView :View, listener: onItemClickListtner) : RecyclerView.ViewHolder(itemView)
    {
        val car_name : TextView = itemView.findViewById(R.id.textNameCar)
        val car_class : TextView = itemView.findViewById(R.id.textClassCar)
        val car_gearbox : TextView = itemView.findViewById(R.id.textGearbox)
        val car_fuel : TextView = itemView.findViewById(R.id.textFuelTank)
        val car_address : TextView = itemView.findViewById(R.id.textCarAddress)
        val car_passengers : TextView = itemView.findViewById(R.id.textNumberOfPersons)
        val car_bags : TextView = itemView.findViewById(R.id.textNumberOfBags)
        val car_rating : TextView = itemView.findViewById(R.id.textRating)
        val car_cost : TextView = itemView.findViewById(R.id.textCostPerDay)

        init {

            itemView.setOnClickListener{

                listener.onItemClick(adapterPosition)

            }

        }

    }
}
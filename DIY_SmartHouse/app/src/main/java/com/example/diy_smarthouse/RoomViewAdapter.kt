package com.example.diy_smarthouse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoomViewAdapter(private val deviceList: ArrayList<Device>) : RecyclerView.Adapter<RoomViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.device_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val device : Device = deviceList[position]
        holder.device.text= device.device
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val device: TextView= itemView.findViewById(R.id.textView)
    }
}
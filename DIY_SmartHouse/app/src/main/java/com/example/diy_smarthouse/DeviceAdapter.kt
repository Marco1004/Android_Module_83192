package com.example.diy_smarthouse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeviceAdapter(private val lightList: ArrayList<Light>) : RecyclerView.Adapter<DeviceAdapter.MyViewHolder>() {

    private lateinit var mListener : onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.custom_layout, parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val light : Light = lightList[position]
        holder.intensity.text= light.intensity.toString()
        holder.color.text= light.color.toString()
    }

    override fun getItemCount(): Int {
        return lightList.size
    }

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListener){
        mListener= listener
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        val intensity: TextView= itemView.findViewById(R.id.intensity_value)
        val color:  TextView= itemView.findViewById(R.id.color_value)

        init{
            itemView.setOnClickListener {
                listener.onItemClick(absoluteAdapterPosition)
            }
        }
    }
}
package com.example.deliverable2

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CitiesListAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    var data= listOf<Cities>()
        set(value){
            field= value
            notifyDataSetChanged()
        }
    override fun getItemcount()= data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item= data[position]
        holder.textView.text= item.city.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }
}
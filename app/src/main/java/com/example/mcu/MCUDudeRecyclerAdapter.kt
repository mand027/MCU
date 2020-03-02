package com.example.mcu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.mcu_dude_layout.view.*

class MCUDudeRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dudes: List<MCUDude> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MCUDudeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.mcu_dude_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dudes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class MCUDudeViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.nombre
        val notes = itemView.notas
    }
}
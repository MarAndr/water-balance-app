package com.example.mywaterapp.ui.dashboard

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mywaterapp.R
import com.example.mywaterapp.data.DrinkingWater
import com.example.mywaterapp.utils.inflate


class AdapterWaterList: RecyclerView.Adapter<AdapterWaterList.Holder>() {

    var waterList: List<DrinkingWater> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.item_water_data))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val drinkingWaterItem = waterList[position]
        holder.bind(drinkingWaterItem)
    }

    override fun getItemCount(): Int {
        return waterList.size
    }

    class Holder(view: View): RecyclerView.ViewHolder(view){

        private val timeTextView: TextView = view.findViewById(R.id.tv_itemWaterData_time)
        private val volumeTextView: TextView = view.findViewById(R.id.tv_itemWaterData_volume)


        fun bind(drinkingWaterItem: DrinkingWater){
            timeTextView.text = drinkingWaterItem.time
            volumeTextView.text = drinkingWaterItem.volume.toString()
        }
    }
}
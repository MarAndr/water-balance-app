package com.example.mywaterapp.statistic

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mywaterapp.R
import com.example.mywaterapp.data.sum.DaySum
import com.example.mywaterapp.utils.inflate
import kotlinx.android.extensions.LayoutContainer

class DaySumAdapter: RecyclerView.Adapter<DaySumAdapter.WaterSumViewHolder>() {

    private var daysResult = emptyList<DaySum>()

    fun updateAdapter(newList: List<DaySum>){
        daysResult = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterSumViewHolder {
        return WaterSumViewHolder(parent.inflate(R.layout.item_water_statistic))
    }

    override fun onBindViewHolder(holder: WaterSumViewHolder, position: Int) {
        val dayResult = daysResult[position]
        holder.bind(dayResult)
    }

    override fun getItemCount(): Int {
        return daysResult.size
    }

    class WaterSumViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer{


        val textViewSum = containerView.findViewById<TextView>(R.id.tv_itemWaterData_sum)
        val textViewDay = containerView.findViewById<TextView>(R.id.tv_itemWaterData_day)

        fun bind(dayResult: DaySum){
            textViewSum.text = dayResult.sum.toString()
            textViewDay.text = dayResult.day
        }
    }
}
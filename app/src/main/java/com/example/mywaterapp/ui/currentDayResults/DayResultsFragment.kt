package com.example.mywaterapp.ui.currentDayResults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mywaterapp.R
import com.example.mywaterapp.WaterBalanceViewModel
import com.example.mywaterapp.utils.getCurrentDay
import com.example.mywaterapp.utils.getDayFromFullTime
import kotlinx.android.synthetic.main.fragment_notifications.*
import timber.log.Timber
import java.lang.NullPointerException

class DayResultsFragment : Fragment() {

    private val dayResultViewModel: WaterBalanceViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dayResultViewModel.getAllWater()
        observeLiveData()
    }

    private fun observeLiveData() {
        dayResultViewModel.data.observe(viewLifecycleOwner){ waterList ->
            val currentDay = getCurrentDay()
            try {
                val todayList = waterList.mapNotNull { it.takeIf { currentDay == getDayFromFullTime(it.time) }}
                Timber.d("todayList: $todayList")
                tv_dayResultsFragment_result.text = todayList.map { it.volume }.sum().toString()
            } catch (e: NullPointerException) {
                Timber.d(e)
            }
        }
    }
}
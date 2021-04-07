package com.example.mywaterapp.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywaterapp.R
import com.example.mywaterapp.WaterBalanceViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    companion object {
        private const val TAG = "DashboardFragment"
    }

    private val dashboardViewModel by viewModels<WaterBalanceViewModel>()
    private var adapterWaterList: AdapterWaterList? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        dashboardViewModel.getAllWater()
        observeLiveData()
    }

    private fun init() {
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        adapterWaterList = AdapterWaterList()
        with(rv_dashBoard_waterList) {
            try {
                adapter = adapterWaterList
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                addItemDecoration(divider)
            } catch (e: NullPointerException) {
                Log.d(TAG, "init: ${e.message}")
            }
        }
    }

    private fun observeLiveData() {
        dashboardViewModel.data.observe(viewLifecycleOwner) { waterList ->
            adapterWaterList?.waterList = waterList
            adapterWaterList?.notifyDataSetChanged()
        }
    }
}
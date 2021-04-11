package com.example.mywaterapp.statistic

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywaterapp.ViewBindingFragment
import com.example.mywaterapp.WaterBalanceViewModel
import com.example.mywaterapp.data.sum.DaySum
import com.example.mywaterapp.databinding.FragmentStatisticBinding
import com.example.mywaterapp.utils.getCurrentDay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class StatisticFragment: ViewBindingFragment<FragmentStatisticBinding>(FragmentStatisticBinding::inflate) {

    private val viewModel by viewModels<WaterBalanceViewModel>()
    private var daySumAdapter: DaySumAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getWaterDaySum(getCurrentDay()) {daySum ->
            viewModel.addDaySum(listOf(DaySum(getCurrentDay(), daySum)))
            viewModel.getDaysSumList()
        }
        initList()
        observeLiveData()
    }


    private fun initList(){
        daySumAdapter = DaySumAdapter()
        val divider = DividerItemDecoration(requireContext(), 1)
        with(binding.recyclerViewStatisticFragmentDaySumList){
            adapter = daySumAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(divider)
        }
    }

    private fun observeLiveData(){
        viewModel.daySumList.observe(viewLifecycleOwner){ daySumList ->
            Timber.d("daySumList: $daySumList")
            daySumAdapter?.updateAdapter(daySumList)
            daySumAdapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        daySumAdapter = null
    }
}
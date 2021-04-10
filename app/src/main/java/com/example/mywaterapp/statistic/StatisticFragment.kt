package com.example.mywaterapp.statistic

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mywaterapp.ViewBindingFragment
import com.example.mywaterapp.WaterBalanceViewModel
import com.example.mywaterapp.data.sum.DaySum
import com.example.mywaterapp.databinding.FragmentStatisticBinding
import com.example.mywaterapp.utils.getCurrentDay
import kotlinx.android.synthetic.main.fragment_statistic.*

class StatisticFragment: ViewBindingFragment<FragmentStatisticBinding>(FragmentStatisticBinding::inflate) {

    private val viewModel by viewModels<WaterBalanceViewModel>()
    private var daySumAdapter: DaySumAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        viewModel.getWaterDaySum(getCurrentDay())
        viewModel.getAllDaysSum()
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
        viewModel.waterDaySum.observe(viewLifecycleOwner){daySum ->
            viewModel.addDaySum(listOf(DaySum(getCurrentDay(), daySum)))
        }

        viewModel.allDaySum.observe(viewLifecycleOwner){allDaySum ->
            daySumAdapter?.updateAdapter(allDaySum)
            daySumAdapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        daySumAdapter = null
    }
}
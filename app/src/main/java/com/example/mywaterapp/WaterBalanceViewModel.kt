package com.example.mywaterapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywaterapp.data.DrinkingWater
import com.example.mywaterapp.data.sum.DaySum
import com.example.mywaterapp.utils.SavingSPHelper
import com.example.mywaterapp.utils.getCurrentDay
import com.example.mywaterapp.utils.getDayFromFullTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import timber.log.Timber

class WaterBalanceViewModel : ViewModel() {

    private val repository = WaterBalanceRepository()
    private val _data = MutableLiveData<List<DrinkingWater>>()
    val data: LiveData<List<DrinkingWater>> = _data
    val waterBalanceLiveData = getAllWaterLiveData()
    private val _daySum = MutableLiveData<Long>()
    val daySum: LiveData<Long> = _daySum
    private val _waterDaySum = MutableLiveData<Double>()
    val waterDaySum: LiveData<Double> = _waterDaySum
    private val _isItFirstRun = MutableLiveData<Boolean>()
    val isItFirstRun: LiveData<Boolean> = _isItFirstRun
    private val _daySumList = MutableLiveData<List<DaySum>>()
    val daySumList: LiveData<List<DaySum>> = _daySumList

    fun getAllWater() {
        viewModelScope.launch {
            _data.postValue(repository.getDrinkingWater())
        }

    }

    private fun getAllWaterLiveData() = repository.getDrinkingWaterLiveData()

    fun addWater(waterList: List<DrinkingWater>) {
        viewModelScope.launch {
            repository.addWater(waterList)
        }
    }

    fun getDaySum() {
        viewModelScope.launch {
            val currentDay = getCurrentDay()
            val todayList = repository.getDrinkingWater()
                .mapNotNull { it.takeIf { currentDay == getDayFromFullTime(it.time) } }
            _daySum.value = todayList.map { it.volume }.sum()
        }
    }

    fun checkIsItFirstRun() {
        _isItFirstRun.postValue(SavingSPHelper.isFirstRunFlag)
    }

    fun saveWaterBalanceNormInSharedPreferences(sex: String, weight: Int){
        viewModelScope.launch {
            repository.saveWaterBalanceNormInSharedPreferences(sex, weight)
        }
    }

    fun getWaterDaySum(day: String, callback: (Double) -> Unit){
        viewModelScope.launch {
            callback(repository.getWaterDaySum(day))
        }
    }

    fun addDaySum(daySum: List<DaySum>){
        viewModelScope.launch {
            Timber.d("addDaySum viewModel")
            repository.addDaySum(daySum)
        }
    }

    fun getDaysSumList(){
        viewModelScope.launch {
            Timber.d("getDaysSumList viewModel")
        _daySumList.value = repository.getDaysSumList()
        }
    }


}
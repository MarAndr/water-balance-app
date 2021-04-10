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
    private val _allDaySum = MutableLiveData<List<DaySum>>()
    val allDaySum: LiveData<List<DaySum>> = _allDaySum

    fun getAllWater() {
        Timber.d("_data.value = ${_data.value}")
        viewModelScope.launch {
            _data.postValue(repository.getDrinkingWater())
            Timber.d("_data.value = ${_data.value}")
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
            Timber.d("daySum = ${_daySum.value}")
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

    fun getWaterDaySum(day: String){
        viewModelScope.launch(IO) {
            _waterDaySum.postValue(repository.getWaterDaySum(day))
        }
    }

    fun addDaySum(daySum: List<DaySum>){
        viewModelScope.launch {
            repository.addDaySum(daySum)
        }
    }

    fun getAllDaysSum(){
        viewModelScope.launch {
        _allDaySum.value = repository.getAllDaysSum()
        }
    }


}
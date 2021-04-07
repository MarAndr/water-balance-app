package com.example.mywaterapp.ui.currentDayResults

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DayResultsViewModel : ViewModel() {

    private val repo = DayResultsRepository()
    private val _dayResult = MutableLiveData<String>().apply {
        value = "0"
    }
    val dayResult: LiveData<String> = _dayResult

    fun getResult(){
        _dayResult.postValue(repo.getVolumesSum().toString())
    }
}
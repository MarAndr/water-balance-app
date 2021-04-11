package com.example.mywaterapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mywaterapp.utils.SavingSPHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

class StartActivity: AppCompatActivity() {

    private val viewModel: WaterBalanceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SavingSPHelper.initHelper(this)
        viewModel.checkIsItFirstRun()
        observeLiveData()
        setContentView(R.layout.activity_start)
    }

    private fun observeLiveData() {
        viewModel.isItFirstRun.observe(this){ isFirstRun ->
            if (isFirstRun){
                Timber.d("first run")
                Intent(this, FirstRunActivity::class.java).also {
                    startActivity(it)
                }
            }else{
                Timber.d("not first run")
                Intent(this, NotFirstRunActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}
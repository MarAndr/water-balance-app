package com.example.mywaterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mywaterapp.utils.SavingSPHelper
import com.google.android.material.bottomnavigation.BottomNavigationView


class NotFirstRunActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SavingSPHelper.initHelper(this)
//        setTheme(R.stylme.Theme_MyWaterApp)
        setContentView(R.layout.activity_not_first_run)
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val navController = findNavController(R.id.fragment)
        navView.setupWithNavController(navController)

    }
}
package com.example.mywaterapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class FirstRunActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_run)
        addFragment()
    }


    private fun addFragment(){
        supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout_firstRunActivity, FragmentStartOptions())
                .commit()
    }
}
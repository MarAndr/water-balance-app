package com.example.mywaterapp.ui.home

import android.content.Context
import android.content.SharedPreferences

object SavingSPHelper {
    var context: Context? = null
    private const val SP_WATERBALANCE_NORM = "sp_waterbalance_norm"
    lateinit var spWaterNorm: SharedPreferences
    lateinit var spWitMySettings: SharedPreferences
    var isFirstRunFlag: Boolean = true
    private const val MY_SETTINGS = "my_settings"

    fun initHelper(setContext: Context){
        context = setContext
        spWaterNorm = context!!.getSharedPreferences(SP_WATERBALANCE_NORM, Context.MODE_PRIVATE)
        spWitMySettings = context?.getSharedPreferences(
                MY_SETTINGS,
                Context.MODE_PRIVATE
        )!!
        checkIfItFirstRun()
    }

    private fun checkIfItFirstRun(): Boolean {
        return try {
            if (spWitMySettings.getBoolean("hasVisited", false)) {
                isFirstRunFlag = false
                false
            } else {
                isFirstRunFlag = true
                val editor = spWitMySettings.edit()
                editor?.putBoolean("hasVisited", true)
                editor?.apply()
                true
            }
        } catch (e: Exception) {
            false
        }
    }
}
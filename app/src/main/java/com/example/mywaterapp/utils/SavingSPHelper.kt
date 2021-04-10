package com.example.mywaterapp.utils

import android.content.Context
import android.content.SharedPreferences

object SavingSPHelper {
    var context: Context? = null
    const val USER_WEIGHT = "user_weight"
    const val USER_GENDER = "user_gender"
    private const val SP_WATERBALANCE_NORM = "sp_waterbalance_norm"
    private const val SP_USER_SETTINGS = "sp_user_settings"
    lateinit var spWaterNorm: SharedPreferences
    lateinit var spUserSettings: SharedPreferences
    lateinit var spIsFirstInit: SharedPreferences
    var isFirstRunFlag: Boolean = true
    private const val IS_FIRST_CHECKER = "is_first_checker"

    fun initHelper(setContext: Context){
        context = setContext
        spWaterNorm = context!!.getSharedPreferences(SP_WATERBALANCE_NORM, Context.MODE_PRIVATE)
        spUserSettings = context!!.getSharedPreferences(SP_USER_SETTINGS, Context.MODE_PRIVATE)
        spIsFirstInit = context?.getSharedPreferences(
                IS_FIRST_CHECKER,
                Context.MODE_PRIVATE
        )!!
        checkIfItFirstRun()
    }

    private fun checkIfItFirstRun(): Boolean {
        return try {
            if (spIsFirstInit.getBoolean("hasVisited", false)) {
                isFirstRunFlag = false
                false
            } else {
                isFirstRunFlag = true
                val editor = spIsFirstInit.edit()
                editor?.putBoolean("hasVisited", true)
                editor?.apply()
                true
            }
        } catch (e: Exception) {
            false
        }
    }
}
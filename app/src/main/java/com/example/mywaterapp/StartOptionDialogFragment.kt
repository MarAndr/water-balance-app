package com.example.mywaterapp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_start_option.view.*
import kotlinx.coroutines.launch

class StartOptionDialogFragment: DialogFragment(){

    lateinit var mView: View
    private val repo = WaterBalanceRepository()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_start_option, null)

        return MaterialAlertDialogBuilder(requireContext())
                .setView(mView)
                .setTitle("Insert some data to calculate your water norm")
                .setPositiveButton("ok"){_,_ ->
                    val sex = if (mView.radioButton_startOptionDialog_female.isChecked) "Male" else "Female"
                    val weight = mView.et_startOptionDialog_weight.text.toString()
                    lifecycleScope.launch {
                    repo.saveWaterBalanceNormInSharedPreferences(sex, weight.toInt())
                    }
                }
                .setNegativeButton("cancel"){_,_ ->}
                .create()
    }
}
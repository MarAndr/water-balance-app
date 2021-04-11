package com.example.mywaterapp.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.mywaterapp.R
import com.example.mywaterapp.WaterBalanceViewModel
import com.example.mywaterapp.utils.SavingSPHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_change_gender.view.*
import timber.log.Timber

class ChangeGengerDialog(private val callback: (String) -> Unit): DialogFragment() {

    lateinit var mView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Timber.d("onCreateDialog")
        mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_change_gender, null)
        return MaterialAlertDialogBuilder(requireContext())
            .setView(mView)
            .setTitle("Change your gender")
            .setPositiveButton("change"){_,_ ->
                Timber.d("positiveButtonClick")
                callback(getRadioButtonValue())
            }
            .setNegativeButton("cancel"){_,_ ->}
            .create()
    }

    private fun getRadioButtonValue(): String{
        return when (mView.radioGroup_dialogChangeGender.checkedRadioButtonId){
            R.id.radioButton_dialogChangeGender_male -> "Male"
            R.id.radioButton_dialogChangeGender_female -> "Female"
            else -> "zopa"
        }
    }
}
package com.example.mywaterapp.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.mywaterapp.R
import com.example.mywaterapp.WaterBalanceViewModel
import com.example.mywaterapp.utils.SavingSPHelper
import com.example.mywaterapp.utils.Weight
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_change_weight.view.*
import timber.log.Timber

class ChangeWeightDialog(private val viewModel: WaterBalanceViewModel): DialogFragment() {

    lateinit var mView: View
    private var weight: Int? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_change_weight, null)
        mView.scroll_choice_dialogChangeWeight.addItems(Weight.weights,70)
        mView.scroll_choice_dialogChangeWeight.visibleItemCount = 2
        val currentGender = SavingSPHelper.spUserSettings.getString(SavingSPHelper.USER_GENDER, "")
        mView.scroll_choice_dialogChangeWeight.setOnItemSelectedListener { scrollChoice, position, name ->
            Timber.d("name = $name")
            weight = name.toInt()
        }
        return MaterialAlertDialogBuilder(requireContext())
            .setView(mView)
            .setTitle("Change yout weight")
            .setPositiveButton("change"){_,_ ->
                Timber.d("positive button click")
                try {
                    viewModel.saveWaterBalanceNormInSharedPreferences(currentGender!!, weight!!)
                } catch (e: NullPointerException) {
                    Timber.d("weight = $weight")
                    Timber.d(e)
                }
            }
            .setNegativeButton("cancel"){_,_ ->}
            .create()
    }
}
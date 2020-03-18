package com.globant.onboardingcalculatorwithkotlin.mvp.view

import android.app.Activity
import android.widget.TextView
import android.widget.Toast
import com.globant.onboardingcalculatorwithkotlin.R
import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_ZERO
import kotlinx.android.synthetic.main.activity_main.visor

class CalculatorView(activity: Activity) : ActivityView(activity), CalculatorContracts.View {

    private val visor: TextView = activity.visor

    override fun refreshVisor(value: String) {
        visor.text = value
    }

    override fun clearVisor() {
        visor.text = NUMBER_ZERO
        showOperationCleaned()
    }

    private fun showMessage(messageId: Int) {
        Toast.makeText(activity, messageId, Toast.LENGTH_LONG).show()
    }

    override fun showOperatorError() {
        showMessage(R.string.operator_error_msj);
    }

    override fun showOperationCleaned() {
        showMessage(R.string.operation_cleaned)
    }

    override fun showDecimalError() {
        showMessage(R.string.decimal_error_msj)
    }
}
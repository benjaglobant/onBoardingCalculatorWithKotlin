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
    }

    private fun showMessage(message: Int) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showIncompleteOperationErrorMessage() {
        showMessage(R.string.incomplete_operation_msj)
    }

    override fun showDecimalErrorMessage() {
        showMessage(R.string.decimal_error_msj)
    }

    override fun showInvalidDivision() {
        showMessage(R.string.math_error_msj)
    }

    override fun showOperatorErrorMessage() {
        showMessage(R.string.operator_error_msj)
    }
}
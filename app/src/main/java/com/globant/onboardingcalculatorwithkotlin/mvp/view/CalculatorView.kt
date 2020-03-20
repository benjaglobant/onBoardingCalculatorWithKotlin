package com.globant.onboardingcalculatorwithkotlin.mvp.view

import android.app.Activity
import android.provider.Settings.Global.getString
import android.widget.TextView
import android.widget.Toast
import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts
import com.globant.onboardingcalculatorwithkotlin.mvp.presenter.CalculatorError
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_ZERO
import kotlinx.android.synthetic.main.activity_main.visor

class CalculatorView(activity: Activity) : ActivityView(activity), CalculatorContracts.View {

    private val visor: TextView = activity.visor

    override fun refreshVisor(value: String) {
        visor.text = value
    }

    override fun clearVisor() {
        visor.text = NUMBER_ZERO
        showMessage(CalculatorError.OPERATION_CLEANED)
    }

    override fun showMessage(error: CalculatorError) {
        Toast.makeText(activity, error.getErrorMessage(), Toast.LENGTH_LONG).show()
    }
}
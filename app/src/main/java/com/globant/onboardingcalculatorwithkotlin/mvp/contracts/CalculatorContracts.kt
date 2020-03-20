package com.globant.onboardingcalculatorwithkotlin.mvp.contracts

import com.globant.onboardingcalculatorwithkotlin.mvp.presenter.CalculatorError

interface CalculatorContracts {
    interface Model {
        var first_operand: String
        var second_operand: String
        var operator: Char
        var result: String
    }

    interface View {
        fun refreshVisor(value: String)
        fun clearVisor()
        fun showMessage(message: CalculatorError)
    }

    interface Presenter {
        fun updateVisor()
        fun onClearButtonPressed()
        fun onNumberPressed(number: String)
        fun onOperatorPressed(operator: Char)
        fun onPointPressed()
        fun onEqualPressed()
        fun onDeletePressed()
    }
}
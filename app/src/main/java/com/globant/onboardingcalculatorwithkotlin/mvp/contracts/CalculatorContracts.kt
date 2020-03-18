package com.globant.onboardingcalculatorwithkotlin.mvp.contracts

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
        fun showOperatorError()
        fun showOperationCleaned()
        fun showDecimalError()
    }

    interface Presenter {
        fun updateVisor()
        fun onClearButtonPressed()
        fun onNumberPressed(number: String)
        fun onOperatorPressed(operator: Char)
        fun onPointPressed()
    }
}
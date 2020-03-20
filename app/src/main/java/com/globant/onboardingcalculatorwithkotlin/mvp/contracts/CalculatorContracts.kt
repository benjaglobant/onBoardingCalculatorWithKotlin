package com.globant.onboardingcalculatorwithkotlin.mvp.contracts

interface CalculatorContracts {
    interface Model {
        var first_operand: String
        var second_operand: String
        var operator: Char
        var result: String
        fun calculate(): String
        fun clear()
    }

    interface View {
        fun refreshVisor(value: String)
        fun clearVisor()
        fun showOperatorErrorMessage()
        fun showDecimalErrorMessage()
        fun showIncompleteOperationErrorMessage()
        fun showInvalidDivision()
    }

    interface Presenter {
        fun updateVisor()
        fun onClearButtonPressed()
        fun onNumberPressed(number: String)
        fun onOperatorPressed(operator: Char)
        fun onPointPressed()
        fun onEqualPressed()
    }
}
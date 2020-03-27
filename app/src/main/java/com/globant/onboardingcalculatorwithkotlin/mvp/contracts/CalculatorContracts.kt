package com.globant.onboardingcalculatorwithkotlin.mvp.contracts

interface CalculatorContracts {
    interface Model {
        fun calculate(): String
        fun clear()
        fun isFullOperation(): Boolean
        fun currentValue(): String
        fun appendNumber(number: String): Boolean
        fun saveOperator(operator: Char): Boolean
        fun appendDecimalPoint(): Boolean
        fun deleteDigit(): Boolean
    }

    interface View {
        fun refreshVisor(value: String)
        fun clearVisor()
        fun showMessage(message: String)
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
        fun onDeletePressed()
    }
}
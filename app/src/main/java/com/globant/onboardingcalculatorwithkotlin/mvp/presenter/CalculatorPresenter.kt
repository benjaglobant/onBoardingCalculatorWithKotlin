package com.globant.onboardingcalculatorwithkotlin.mvp.presenter

import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts

class CalculatorPresenter(
    private val model: CalculatorContracts.Model,
    private val view: CalculatorContracts.View
) : CalculatorContracts.Presenter {

    override fun updateVisor() {
        view.refreshVisor(model.currentValue())
    }

    override fun onClearButtonPressed() {
        model.clear()
        view.clearVisor()
    }

    override fun onNumberPressed(number: String) {
        if (!model.appendNumber(number)) {
            view.showOperatorErrorMessage()
        }
        updateVisor()
    }

    override fun onOperatorPressed(operator: Char) {
        if (!model.saveOperator(operator)) {
            view.showOperatorErrorMessage()
        }
        updateVisor()
    }

    override fun onPointPressed() {
        if (!model.appendDecimalPoint()) {
            view.showDecimalErrorMessage()
        } else {
            updateVisor()
        }
    }

    override fun onEqualPressed() {
        if (model.isFullOperation()) {
            val result = model.calculate()
            if (result.isNotEmpty()) {
                view.refreshVisor(result)
            } else {
                model.clear()
                view.refreshVisor(model.currentValue())
                view.showInvalidDivision()
            }
        } else {
            view.showIncompleteOperationErrorMessage()
        }
    }

    override fun onDeletePressed() {
        if (!model.deleteDigit()) {
            view.showIncompleteOperationErrorMessage()
        } else {
            updateVisor()
        }
    }
}
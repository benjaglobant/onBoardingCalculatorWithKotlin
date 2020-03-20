package com.globant.onboardingcalculatorwithkotlin.mvp.presenter

import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.DECIMAL_POINT
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_CHAR
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_STRING
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_ZERO

class CalculatorPresenter(
    private val model: CalculatorContracts.Model,
    private val view: CalculatorContracts.View
) : CalculatorContracts.Presenter {

    override fun updateVisor() {
        if (model.first_operand.isEmpty()) {
            view.refreshVisor(NUMBER_ZERO)
        } else if ((model.second_operand.isEmpty()) && (model.operator == EMPTY_CHAR)) {
            view.refreshVisor(model.first_operand)
        } else if ((model.operator != EMPTY_CHAR) && (model.second_operand.isEmpty())) {
            view.refreshVisor(model.operator.toString())
        } else if ((!model.second_operand.isEmpty()) && (model.operator != EMPTY_CHAR)) {
            view.refreshVisor(model.second_operand)
        }
    }

    override fun onClearButtonPressed() {
        model.clear()
        view.clearVisor()
    }

    override fun onNumberPressed(number: String) {
        if (model.operator == EMPTY_CHAR) {
            if (model.second_operand.isEmpty()) {
                if (model.result.isEmpty()) {
                    model.first_operand = "${model.first_operand}$number"
                } else {
                    view.showOperatorErrorMessage()
                }
            }
        } else {
            model.second_operand = "${model.second_operand}$number"
        }
        updateVisor()
    }

    override fun onOperatorPressed(operator: Char) {
        if (model.first_operand.isEmpty()) {
            view.showOperatorErrorMessage()
        } else if ((model.operator == EMPTY_CHAR) && (model.second_operand.isEmpty())) {
            model.operator = operator
        } else if (model.operator != EMPTY_CHAR) {
            view.showOperatorErrorMessage()
        }
        updateVisor()
    }

    override fun onPointPressed() {
        if ((model.second_operand.isEmpty()) && (model.operator == EMPTY_CHAR)) {
            if (model.first_operand.isEmpty()) {
                model.first_operand = "$NUMBER_ZERO$DECIMAL_POINT"
            } else if (!model.first_operand.contains(DECIMAL_POINT)) {
                model.first_operand = "${model.first_operand}$DECIMAL_POINT"
            } else {
                view.showDecimalErrorMessage()
            }
        } else if (model.operator != EMPTY_CHAR) {
            if (model.second_operand.isEmpty()) {
                model.second_operand = "$NUMBER_ZERO$DECIMAL_POINT"
            } else if (!model.second_operand.contains(DECIMAL_POINT)) {
                model.second_operand = "${model.second_operand}$DECIMAL_POINT"
            } else {
                view.showDecimalErrorMessage()
            }
        }
        updateVisor()
    }

    override fun onEqualPressed() {
        if ((model.first_operand.isNotEmpty()) && (model.second_operand.isNotEmpty()) && (model.operator != EMPTY_CHAR)) {
            model.first_operand = model.calculate()
            if (model.first_operand.isNotEmpty()) {
                model.result = model.first_operand
                model.second_operand = EMPTY_STRING
                model.operator = EMPTY_CHAR
                view.refreshVisor(model.result)
            } else {
                onClearButtonPressed()
                view.showInvalidDivision()
                view.clearVisor()
            }
        } else {
            view.showIncompleteOperationErrorMessage()
        }
    }
}
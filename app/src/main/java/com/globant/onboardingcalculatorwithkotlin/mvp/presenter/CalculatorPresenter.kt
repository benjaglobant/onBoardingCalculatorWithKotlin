package com.globant.onboardingcalculatorwithkotlin.mvp.presenter

import com.globant.onboardingcalculatorwithkotlin.R
import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.DECIMAL_FORMAT
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.DECIMAL_POINT
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_CHAR
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_STRING
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_ZERO
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_DIVIDE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_MULTIPLY
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_PLUS
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_SUBSTRACTION
import java.text.DecimalFormat

class CalculatorPresenter(
    private val model: CalculatorContracts.Model,
    private val view: CalculatorContracts.View
) : CalculatorContracts.Presenter {

    //private val decimalFormat: DecimalFormat = DecimalFormat(DECIMAL_FORMAT)

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
        model.first_operand = EMPTY_STRING
        model.second_operand = EMPTY_STRING
        model.operator = EMPTY_CHAR
        model.result = EMPTY_STRING
        view.clearVisor()
    }

    override fun onNumberPressed(number: String) {
        if (model.operator == EMPTY_CHAR) {
            if (model.second_operand.isEmpty()) {
                if (model.result.isEmpty()) {
                    model.first_operand = "${model.first_operand}$number"
                } else {
                    view.showMessage(CalculatorError.OPERATOR_ERROR)
                }
            }
        } else {
            model.second_operand = "${model.second_operand}$number"
        }
        updateVisor()
    }

    override fun onOperatorPressed(operator: Char) {
        if (model.first_operand.isEmpty()) {
            view.showMessage(CalculatorError.OPERATOR_ERROR)
        } else if ((model.operator == EMPTY_CHAR) && (model.second_operand.isEmpty())) {
            model.operator = operator
        } else if (model.operator != EMPTY_CHAR) {
            view.showMessage(CalculatorError.OPERATOR_ERROR)
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
                view.showMessage(CalculatorError.DECIMAL_ERROR)
            }
        } else if (model.operator != EMPTY_CHAR) {
            if (model.second_operand.isEmpty()) {
                model.second_operand = "$NUMBER_ZERO$DECIMAL_POINT"
            } else if (!model.second_operand.contains(DECIMAL_POINT)) {
                model.second_operand = "${model.second_operand}$DECIMAL_POINT"
            } else {
                view.showMessage(CalculatorError.DECIMAL_ERROR)
            }
        }
        updateVisor()
    }

    private fun validOperation(): Boolean {
        if (!model.second_operand.equals(NUMBER_ZERO))
            return true
        return false
    }

    private fun calculate(): String = when {
        model.operator === OPERATOR_PLUS -> model.first_operand.toDouble() + model.second_operand.toDouble()
        model.operator === OPERATOR_SUBSTRACTION -> model.first_operand.toDouble() - model.second_operand.toDouble()
        model.operator === OPERATOR_MULTIPLY -> model.first_operand.toDouble() * model.second_operand.toDouble()
        model.operator === OPERATOR_DIVIDE -> {
            if (validOperation()) {
                model.first_operand.toDouble() / model.second_operand.toDouble()
            } else {
                view.showMessage(CalculatorError.MATH_ERROR)
                onClearButtonPressed()
                EMPTY_STRING
            }
        }
        else -> EMPTY_STRING
    }.toString()

    override fun onEqualPressed() {
        if ((model.first_operand.isNotEmpty()) && (model.second_operand.isNotEmpty()) && (model.operator != EMPTY_CHAR)) {
            model.first_operand = calculate()
            model.result = model.first_operand
            model.second_operand = EMPTY_STRING
            model.operator = EMPTY_CHAR
            if(model.first_operand.isNotEmpty()){
                view.refreshVisor(model.result)
            }else{
                view.clearVisor()
            }
        } else {
            view.showMessage(CalculatorError.INCOMPLETE_OPERATION)
        }
    }
}

enum class CalculatorError(val message: Int) {
    DECIMAL_ERROR(R.string.decimal_error_msj),
    OPERATOR_ERROR(R.string.operator_error_msj),
    MATH_ERROR(R.string.math_error_msj),
    INCOMPLETE_OPERATION(R.string.incomplete_operation_msj),
    OPERATION_CLEANED(R.string.operation_cleaned)
}
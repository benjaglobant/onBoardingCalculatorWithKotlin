package com.globant.onboardingcalculatorwithkotlin.mvp.model

import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_CHAR
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_STRING
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_ZERO
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_DIVIDE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_MULTIPLY
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_PLUS
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_SUBSTRACTION

class CalculatorModel : CalculatorContracts.Model {
    override var first_operand: String = EMPTY_STRING
    override var second_operand: String = EMPTY_STRING
    override var operator: Char = EMPTY_CHAR
    override var result: String = EMPTY_STRING

    override fun clear() {
        first_operand = EMPTY_STRING
        second_operand = EMPTY_STRING
        operator = EMPTY_CHAR
        result = EMPTY_STRING
    }

    private fun validDivision():String {
        if (second_operand.equals(NUMBER_ZERO))
            return EMPTY_STRING
        else
            return (first_operand.toDouble() / second_operand.toDouble()).toString()
    }

    override fun calculate(): String = when {
        operator === OPERATOR_PLUS -> first_operand.toDouble() + second_operand.toDouble()
        operator === OPERATOR_SUBSTRACTION -> first_operand.toDouble() - second_operand.toDouble()
        operator === OPERATOR_MULTIPLY -> first_operand.toDouble() * second_operand.toDouble()
        operator === OPERATOR_DIVIDE -> validDivision()
        else -> EMPTY_STRING
    }.toString()
}
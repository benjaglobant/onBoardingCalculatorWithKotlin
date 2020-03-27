package com.globant.onboardingcalculatorwithkotlin.mvp.model

import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts
import com.globant.onboardingcalculatorwithkotlin.utils.Constants
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_CHAR
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_STRING
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_ZERO
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_DIVIDE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_MULTIPLY
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_PLUS
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_SUBSTRACTION

class CalculatorModel : CalculatorContracts.Model {
    private var first_operand: String = EMPTY_STRING
    private var second_operand: String = EMPTY_STRING
    private var operator: Char = EMPTY_CHAR
    private var result: String = EMPTY_STRING

    override fun clear() {
        first_operand = EMPTY_STRING
        second_operand = EMPTY_STRING
        operator = EMPTY_CHAR
        result = EMPTY_STRING
    }

    private fun validDivision(): String {
        if (second_operand.equals(NUMBER_ZERO))
            return EMPTY_STRING
        else
            return (first_operand.toDouble() / second_operand.toDouble()).toString()
    }

    override fun calculate(): String {
        result = when {
            operator === OPERATOR_PLUS -> first_operand.toDouble() + second_operand.toDouble()
            operator === OPERATOR_SUBSTRACTION -> first_operand.toDouble() - second_operand.toDouble()
            operator === OPERATOR_MULTIPLY -> first_operand.toDouble() * second_operand.toDouble()
            operator === OPERATOR_DIVIDE -> validDivision()
            else -> EMPTY_STRING
        }.toString()

        first_operand = result
        second_operand = EMPTY_STRING
        operator = EMPTY_CHAR

        return result
    }

    override fun isFullOperation(): Boolean {
        return ((first_operand.isNotEmpty()) && (second_operand.isNotEmpty()) && (operator != EMPTY_CHAR))
    }

    override fun currentValue(): String {
        if (first_operand.isEmpty()) {
            return NUMBER_ZERO
        } else if ((second_operand.isEmpty()) && (operator == EMPTY_CHAR)) {
            return first_operand
        } else if ((operator != EMPTY_CHAR) && (second_operand.isEmpty())) {
            return operator.toString()
        } else if ((!second_operand.isEmpty()) && (operator != EMPTY_CHAR)) {
            return second_operand
        }
        return NUMBER_ZERO
    }

    override fun appendNumber(number: String): Boolean {
        if (operator == EMPTY_CHAR) {
            if (second_operand.isEmpty()) {
                if (result.isEmpty()) {
                    first_operand = "${first_operand}$number"
                } else {
                    return false
                }
            }
        } else {
            second_operand = "${second_operand}$number"
        }
        return true
    }

    override fun saveOperator(operator: Char): Boolean {
        if (first_operand.isEmpty() || this.operator != EMPTY_CHAR)
            return false
        else if (second_operand.isEmpty())
            this.operator = operator
        else
            return false
        return true
    }

    override fun appendDecimalPoint(): Boolean {
        if ((second_operand.isEmpty()) && (operator == EMPTY_CHAR)) {
            if (first_operand.isEmpty()) {
                first_operand = "$NUMBER_ZERO${Constants.DECIMAL_POINT}"
            } else if (!first_operand.contains(Constants.DECIMAL_POINT)) {
                first_operand = "${first_operand}${Constants.DECIMAL_POINT}"
            } else {
                return false
            }
        } else if (operator != EMPTY_CHAR) {
            if (second_operand.isEmpty()) {
                second_operand = "$NUMBER_ZERO${Constants.DECIMAL_POINT}"
            } else if (!second_operand.contains(Constants.DECIMAL_POINT)) {
                second_operand = "${second_operand}${Constants.DECIMAL_POINT}"
            } else {
                return false
            }
        }
        return true
    }

    override fun deleteDigit(): Boolean {
        if ((second_operand.isEmpty()) && (operator == EMPTY_CHAR) && (first_operand.isNotEmpty())) {
            first_operand = first_operand.substring(0, first_operand.length - 1)
        } else if (first_operand.isEmpty()) {
            return false
        } else if ((second_operand.isEmpty()) && (operator != EMPTY_CHAR)) {
            operator = EMPTY_CHAR
        } else if ((second_operand.isNotEmpty()) && (operator != EMPTY_CHAR)) {
            second_operand = second_operand.substring(0, second_operand.length - 1)
        }
        return true
    }
}
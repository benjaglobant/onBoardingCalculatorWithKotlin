package com.globant.onboardingcalculatorwithkotlin.mvp.model

import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_CHAR
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_STRING

class CalculatorModel : CalculatorContracts.Model {
    override var first_operand: String = EMPTY_STRING
    override var second_operand: String = EMPTY_STRING
    override var operator: Char = EMPTY_CHAR
    override var result: String = EMPTY_STRING
}
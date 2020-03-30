package com.globant.onboardingcalculatorwithkotlin

import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts
import com.globant.onboardingcalculatorwithkotlin.mvp.model.CalculatorModel
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.DECIMAL_POINT
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_STRING
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_EIGHT
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_FIVE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_FOUR
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_ONE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_THREE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_TWO
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_ZERO
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_DIVIDE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_MULTIPLY
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_PLUS
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_SUBSTRACTION
import org.junit.Test

class CalculatorModelTest {

    private val model: CalculatorContracts.Model = CalculatorModel()

    @Test
    fun `testing that one plus one results in two effectively`() {
        model.appendNumber(NUMBER_ONE)
        model.saveOperator(OPERATOR_PLUS)
        model.appendNumber(NUMBER_ONE)

        assert("$NUMBER_TWO$DECIMAL_POINT$NUMBER_ZERO" == model.calculate())
    }

    @Test
    fun `testing that five minus three results in two effectively`() {
        model.appendNumber(NUMBER_FIVE)
        model.saveOperator(OPERATOR_SUBSTRACTION)
        model.appendNumber(NUMBER_THREE)

        assert("$NUMBER_TWO$DECIMAL_POINT$NUMBER_ZERO" == model.calculate())
    }

    @Test
    fun `testing that ten multiplied five results in fifty effectively`() {
        model.appendNumber(NUMBER_ONE)
        model.appendNumber(NUMBER_ZERO)
        model.saveOperator(OPERATOR_MULTIPLY)
        model.appendNumber(NUMBER_FIVE)

        assert("$NUMBER_FIVE$NUMBER_ZERO$DECIMAL_POINT$NUMBER_ZERO" == model.calculate())
    }

    @Test
    fun `testing that eight divided two results in four effectively`() {
        model.appendNumber(NUMBER_EIGHT)
        model.saveOperator(OPERATOR_DIVIDE)
        model.appendNumber(NUMBER_TWO)

        assert("$NUMBER_FOUR$DECIMAL_POINT$NUMBER_ZERO" == model.calculate())
    }

    @Test
    fun `testing that division by zero is invalid`() {
        model.appendNumber(NUMBER_EIGHT)
        model.saveOperator(OPERATOR_DIVIDE)
        model.appendNumber(NUMBER_ZERO)

        assert(EMPTY_STRING == model.calculate())
    }

    @Test
    fun `testing that is empty operation retuns true when some variable is empty`() {
        model.appendNumber(NUMBER_EIGHT)
        model.saveOperator(OPERATOR_DIVIDE)

        assert(!model.isFullOperation())
    }

    @Test
    fun `testing that is empty operation retuns false when all the variables have been setted`() {
        model.appendNumber(NUMBER_EIGHT)
        model.saveOperator(OPERATOR_DIVIDE)
        model.appendNumber(NUMBER_FOUR)

        assert(model.isFullOperation())
    }

    @Test
    fun `testing that current value method returns zero when any variable have not been setted yet`() {
        assert(NUMBER_ZERO == model.currentValue())
    }

    @Test
    fun `testing that current value method returns first operand value when its the only one variable setted`() {
        model.appendNumber(NUMBER_EIGHT)

        assert(NUMBER_EIGHT == model.currentValue())
    }

    @Test
    fun `testing that current value method returns operator value when first operand and operator are setted`() {
        model.appendNumber(NUMBER_EIGHT)
        model.saveOperator(OPERATOR_MULTIPLY)

        assert(OPERATOR_MULTIPLY.toString() == model.currentValue())
    }

    @Test
    fun `testing that current value method returns second operand value when all the variables have been setted`() {
        model.appendNumber(NUMBER_EIGHT)
        model.saveOperator(OPERATOR_MULTIPLY)
        model.appendNumber(NUMBER_FIVE)

        assert(NUMBER_FIVE == model.currentValue())
    }

    @Test
    fun `testing that save operator method returns false when any variable have not been setted yet`() {
        model.saveOperator(OPERATOR_MULTIPLY)

        assert(!model.saveOperator(OPERATOR_MULTIPLY))
    }

    @Test
    fun `testing that save operator method returns false when first operand and operator have been setted`() {
        model.appendNumber(NUMBER_THREE)
        model.saveOperator(OPERATOR_MULTIPLY)

        assert(!model.saveOperator(OPERATOR_MULTIPLY))
    }

    @Test
    fun `testing that save operator method returns false when all the variables have been setted`() {
        model.appendNumber(NUMBER_THREE)
        model.saveOperator(OPERATOR_MULTIPLY)
        model.appendNumber(NUMBER_TWO)

        assert(!model.saveOperator(OPERATOR_MULTIPLY))
    }

    @Test
    fun `testing that append decimal point returns true when any variable have not setted yet`() {
        assert(model.appendDecimalPoint())
    }

    @Test
    fun `testing that append decimal point returns true when first operand has integer number`() {
        model.appendNumber(NUMBER_TWO)

        assert(model.appendDecimalPoint())
    }

    @Test
    fun `testing that append decimal point returns true when first operand has integer and operator have been setted`() {
        model.appendNumber(NUMBER_FIVE)
        model.saveOperator(OPERATOR_DIVIDE)

        assert(model.appendDecimalPoint())
    }

    @Test
    fun `testing that append decimal point returns true when first operand and second operand have integer number and operator has been setted`() {
        model.appendNumber(NUMBER_FIVE)
        model.saveOperator(OPERATOR_MULTIPLY)
        model.appendNumber(NUMBER_TWO)

        assert(model.appendDecimalPoint())
    }

    @Test
    fun `testing that append decimal point returns false when first operand has decimal number`() {
        model.appendNumber(NUMBER_THREE)
        model.appendDecimalPoint()

        model.appendDecimalPoint()

        assert(!model.appendDecimalPoint())
    }

    @Test
    fun `testing that append decimal point returns false when second operand has decimal number`() {
        model.appendNumber(NUMBER_THREE)
        model.saveOperator(OPERATOR_SUBSTRACTION)
        model.appendNumber(NUMBER_EIGHT)
        model.appendDecimalPoint()

        model.appendDecimalPoint()

        assert(!model.appendDecimalPoint())
    }

    @Test
    fun `testing that delete digit method returns false when any variable haven´t been setted yet`() {
        assert(!model.deleteDigit())
    }

    @Test
    fun `testing that delete digit method returns true when first operand has been setted`() {
        model.appendNumber(NUMBER_THREE)
        model.appendNumber(NUMBER_FIVE)

        assert(model.deleteDigit())
    }

    @Test
    fun `testing that delete digit method returns true when first operand and operator have been setted`() {
        model.appendNumber(NUMBER_THREE)
        model.appendNumber(NUMBER_FIVE)
        model.saveOperator(OPERATOR_MULTIPLY)

        assert(model.deleteDigit())
    }

    @Test
    fun `testing that delete digit method returns true when all variables have been setted`() {
        model.appendNumber(NUMBER_THREE)
        model.saveOperator(OPERATOR_PLUS)
        model.appendNumber(NUMBER_FIVE)

        assert(model.deleteDigit())
    }
}
package com.globant.onboardingcalculatorwithkotlin

import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts
import com.globant.onboardingcalculatorwithkotlin.mvp.model.CalculatorModel
import com.globant.onboardingcalculatorwithkotlin.mvp.presenter.CalculatorPresenter
import com.globant.onboardingcalculatorwithkotlin.mvp.view.CalculatorView
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.DECIMAL_POINT
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_CHAR
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_STRING
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_EIGHT
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_FIVE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_THREE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_ZERO
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_PLUS
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_SUBSTRACTION
import org.junit.Test
import org.junit.Assert.assertEquals
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class CalculatorPresenterTest {
    private val model: CalculatorContracts.Model = CalculatorModel()
    private val mockedView: CalculatorContracts.View = mock(CalculatorView::class.java)
    private val presenter: CalculatorContracts.Presenter = CalculatorPresenter(model, mockedView)

    @Test
    fun `on clear button pressed with first operand with operator with second operand`() {
        model.first_operand = NUMBER_FIVE
        model.operator = OPERATOR_PLUS
        model.second_operand = NUMBER_THREE
        model.result = NUMBER_EIGHT

        presenter.onClearButtonPressed()

        assertEquals(EMPTY_STRING, model.first_operand)
        assertEquals(EMPTY_STRING, model.second_operand)
        assertEquals(EMPTY_CHAR, model.operator)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).clearVisor()
    }

    @Test
    fun `on number button pressed with empty operation`() {
        presenter.onNumberPressed(NUMBER_FIVE)

        assertEquals(NUMBER_FIVE, model.first_operand)
        assertEquals(EMPTY_STRING, model.second_operand)
        assertEquals(EMPTY_CHAR, model.operator)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).refreshVisor(model.first_operand)
    }

    @Test
    fun `on number pressed with first operand`() {
        model.first_operand = NUMBER_FIVE

        presenter.onNumberPressed(NUMBER_ZERO)

        assertEquals(NUMBER_FIVE + NUMBER_ZERO, model.first_operand)
        assertEquals(EMPTY_STRING, model.second_operand)
        assertEquals(EMPTY_CHAR, model.operator)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).refreshVisor(model.first_operand)
    }

    @Test
    fun `on number pressed with first operand with operator`() {
        model.first_operand = NUMBER_FIVE
        model.operator = OPERATOR_PLUS

        presenter.onNumberPressed(NUMBER_THREE)

        assertEquals(NUMBER_FIVE, model.first_operand)
        assertEquals(NUMBER_THREE, model.second_operand)
        assertEquals(OPERATOR_PLUS, model.operator)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).refreshVisor(model.second_operand)
    }

    @Test
    fun `on number pressed with first operand with operator with second operand`() {
        model.first_operand = NUMBER_FIVE
        model.operator = OPERATOR_PLUS
        model.second_operand = NUMBER_EIGHT

        presenter.onNumberPressed(NUMBER_THREE)

        assertEquals(NUMBER_FIVE, model.first_operand)
        assertEquals(NUMBER_EIGHT + NUMBER_THREE, model.second_operand)
        assertEquals(OPERATOR_PLUS, model.operator)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).refreshVisor(model.second_operand)
    }

    @Test
    fun `on number button pressed with result show error message`() {
        model.result = NUMBER_FIVE

        presenter.onNumberPressed(NUMBER_THREE)

        assertEquals(EMPTY_STRING, model.first_operand)
        assertEquals(EMPTY_STRING, model.second_operand)
        assertEquals(EMPTY_CHAR, model.operator)
        assertEquals(NUMBER_FIVE, model.result)

        verify(mockedView).showOperatorError()
    }

    @Test
    fun `on operator button pressed with empty operation show error message`() {
        presenter.onOperatorPressed(OPERATOR_PLUS)

        assertEquals(EMPTY_STRING, model.first_operand)
        assertEquals(EMPTY_STRING, model.second_operand)
        assertEquals(EMPTY_CHAR, model.operator)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).showOperatorError()
    }

    @Test
    fun `on operator button pressed with first operand`() {
        model.first_operand = NUMBER_FIVE

        presenter.onOperatorPressed(OPERATOR_PLUS)

        assertEquals(NUMBER_FIVE, model.first_operand)
        assertEquals(EMPTY_STRING, model.second_operand)
        assertEquals(OPERATOR_PLUS, model.operator)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).refreshVisor(model.operator.toString())
    }

    @Test
    fun `on operator button pressed with first operand with operator`() {
        model.first_operand = NUMBER_FIVE
        model.operator = OPERATOR_PLUS

        presenter.onOperatorPressed(OPERATOR_SUBSTRACTION)

        assertEquals(NUMBER_FIVE, model.first_operand)
        assertEquals(OPERATOR_SUBSTRACTION, model.operator)
        assertEquals(EMPTY_STRING, model.second_operand)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).refreshVisor(model.operator.toString())
    }

    @Test
    fun `on point button pressed with empty operation`(){
        presenter.onPointPressed()

        assertEquals(NUMBER_ZERO + DECIMAL_POINT, model.first_operand)
        assertEquals(EMPTY_CHAR, model.operator)
        assertEquals(EMPTY_STRING, model.second_operand)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).refreshVisor(model.first_operand)
    }

    @Test
    fun `on point button pressed with first operand`(){
        model.first_operand = NUMBER_FIVE

        presenter.onPointPressed()

        assertEquals(NUMBER_FIVE + DECIMAL_POINT, model.first_operand)
        assertEquals(EMPTY_CHAR, model.operator)
        assertEquals(EMPTY_STRING, model.second_operand)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).refreshVisor(model.first_operand)
    }

    @Test
    fun `on point button pressed with first operand with operator`(){
        model.first_operand = NUMBER_FIVE
        model.operator = OPERATOR_PLUS

        presenter.onPointPressed()

        assertEquals(NUMBER_FIVE, model.first_operand)
        assertEquals(OPERATOR_PLUS, model.operator)
        assertEquals(NUMBER_ZERO + DECIMAL_POINT, model.second_operand)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).refreshVisor(model.second_operand)
    }

    @Test
    fun `on point button pressed with first operand with operator with second operand`(){
        model.first_operand = NUMBER_FIVE
        model.operator = OPERATOR_PLUS
        model.second_operand = NUMBER_EIGHT

        presenter.onPointPressed()

        assertEquals(NUMBER_FIVE, model.first_operand)
        assertEquals(OPERATOR_PLUS, model.operator)
        assertEquals(NUMBER_EIGHT + DECIMAL_POINT, model.second_operand)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).refreshVisor(model.second_operand)
    }

    @Test
    fun `on point button pressed with decimal number in first operand show error message`(){
        model.first_operand = NUMBER_FIVE + DECIMAL_POINT + NUMBER_THREE

        presenter.onPointPressed()

        assertEquals(NUMBER_FIVE + DECIMAL_POINT + NUMBER_THREE, model.first_operand)
        assertEquals(EMPTY_CHAR, model.operator)
        assertEquals(EMPTY_STRING, model.second_operand)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).showDecimalError()
    }

    @Test
    fun `on point button pressed with decimal number in second operand show error message`(){
        model.first_operand = NUMBER_EIGHT
        model.operator= OPERATOR_PLUS
        model.second_operand = NUMBER_FIVE + DECIMAL_POINT + NUMBER_THREE

        presenter.onPointPressed()

        assertEquals(NUMBER_EIGHT, model.first_operand)
        assertEquals(OPERATOR_PLUS, model.operator)
        assertEquals(NUMBER_FIVE + DECIMAL_POINT + NUMBER_THREE, model.second_operand)
        assertEquals(EMPTY_STRING, model.result)

        verify(mockedView).showDecimalError()
    }
}
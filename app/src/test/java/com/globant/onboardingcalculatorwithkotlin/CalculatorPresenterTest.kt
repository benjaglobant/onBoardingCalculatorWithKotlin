package com.globant.onboardingcalculatorwithkotlin

import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts
import com.globant.onboardingcalculatorwithkotlin.mvp.presenter.CalculatorPresenter
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.DECIMAL_POINT
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.EMPTY_STRING
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_EIGHT
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_FIVE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_THREE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_TWO
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_MULTIPLY
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_PLUS
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class CalculatorPresenterTest {

    private val mockedModel: CalculatorContracts.Model = mock()
    private val mockedView: CalculatorContracts.View = mock()
    private val presenter: CalculatorContracts.Presenter =
        CalculatorPresenter(mockedModel, mockedView)

    @Test
    fun `call refresh visor when update visor method is called`() {
        mockedModel.stub { on { currentValue() } doReturn NUMBER_FIVE }

        presenter.updateVisor()

        verify(mockedView).refreshVisor(mockedModel.currentValue())
    }

    @Test
    fun `call clear and refresh visor when clear button is pressed`() {
        presenter.onClearButtonPressed()

        verify(mockedModel).clear()
        verify(mockedView).clearVisor()
    }

    @Test
    fun `call append number and operator error message when number button is pressed`() {
        mockedModel.stub { on { currentValue() } doReturn NUMBER_FIVE }
        mockedModel.stub { on { appendNumber(NUMBER_FIVE) } doReturn false }

        presenter.onNumberPressed(NUMBER_FIVE)

        verify(mockedModel).appendNumber(NUMBER_FIVE)
        verify(mockedView).showOperatorErrorMessage()
    }

    @Test
    fun `call append number and refresh visor when number button is pressed`() {
        mockedModel.stub { on { currentValue() } doReturn NUMBER_FIVE }
        mockedModel.stub { on { appendNumber(NUMBER_FIVE) } doReturn true }

        presenter.onNumberPressed(NUMBER_FIVE)

        verify(mockedModel).appendNumber(NUMBER_FIVE)
        verify(mockedView).refreshVisor(mockedModel.currentValue())
    }

    @Test
    fun `call save operator and operator error message when operator button is pressed`() {
        mockedModel.stub { on { currentValue() } doReturn NUMBER_FIVE }
        mockedModel.stub { on { saveOperator(OPERATOR_PLUS) } doReturn false }

        presenter.onOperatorPressed(OPERATOR_PLUS)

        verify(mockedModel).saveOperator(OPERATOR_PLUS)
        verify(mockedView).showOperatorErrorMessage()
    }

    @Test
    fun `call save operator and refresh visor when operator button is pressed`() {
        mockedModel.stub { on { currentValue() } doReturn NUMBER_FIVE }
        mockedModel.stub { on { saveOperator(OPERATOR_PLUS) } doReturn true }

        presenter.onOperatorPressed(OPERATOR_MULTIPLY)

        verify(mockedModel).saveOperator(OPERATOR_MULTIPLY)
        verify(mockedView).refreshVisor(mockedModel.currentValue())
    }

    @Test
    fun `call append decimal point and show decimal error when decimal point button is pressed`() {
        mockedModel.stub { on { currentValue() } doReturn DECIMAL_POINT }
        mockedModel.stub { on { appendDecimalPoint() } doReturn false }

        presenter.onPointPressed()

        verify(mockedModel).appendDecimalPoint()
        verify(mockedView).showDecimalErrorMessage()
    }

    @Test
    fun `call append decimal point and refresh visor when decimal point button is pressed`() {
        mockedModel.stub { on { currentValue() } doReturn DECIMAL_POINT }
        mockedModel.stub { on { appendDecimalPoint() } doReturn true }

        presenter.onPointPressed()

        verify(mockedModel).appendDecimalPoint()
        verify(mockedView).refreshVisor(mockedModel.currentValue())
    }

    @Test
    fun `call is full operation and calculate and refresh visor when equal button is pressed`() {
        mockedModel.stub { on { isFullOperation() } doReturn true }
        mockedModel.stub { on { currentValue() } doReturn NUMBER_TWO }
        mockedModel.stub { on { calculate() } doReturn NUMBER_EIGHT }

        presenter.onEqualPressed()

        verify(mockedModel).isFullOperation()
        verify(mockedModel).calculate()
        verify(mockedView).refreshVisor(mockedModel.calculate())
    }

    @Test
    fun `call is full operation and calculate and show incomplete operation error when equal button is pressed`() {
        mockedModel.stub { on { isFullOperation() } doReturn true }
        mockedModel.stub { on { currentValue() } doReturn NUMBER_TWO }
        mockedModel.stub { on { calculate() } doReturn EMPTY_STRING }

        presenter.onEqualPressed()

        verify(mockedModel).isFullOperation()
        verify(mockedModel).calculate()
        verify(mockedModel).clear()
        verify(mockedView).refreshVisor(mockedModel.currentValue())
        verify(mockedView).showInvalidDivision()
    }

    @Test
    fun `call is full operation and show incomplete operation error when equal button is pressed`() {
        mockedModel.stub { on { isFullOperation() } doReturn false }

        presenter.onEqualPressed()

        verify(mockedModel).isFullOperation()
        verify(mockedView).showIncompleteOperationErrorMessage()
    }

    @Test
    fun `call delete digit and show incomplete operation error when delete button is pressed`() {
        mockedModel.stub { on { deleteDigit() } doReturn false }

        presenter.onDeletePressed()

        verify(mockedModel).deleteDigit()
        verify(mockedView).showIncompleteOperationErrorMessage()
    }

    @Test
    fun `call delete digit and refresh visor when delete button is pressed`() {
        mockedModel.stub { on { deleteDigit() } doReturn true }
        mockedModel.stub { on { currentValue() } doReturn NUMBER_THREE }

        presenter.onDeletePressed()

        verify(mockedModel).deleteDigit()
        verify(mockedView).refreshVisor(mockedModel.currentValue())
    }
}
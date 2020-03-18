package com.globant.onboardingcalculatorwithkotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.globant.onboardingcalculatorwithkotlin.R
import com.globant.onboardingcalculatorwithkotlin.mvp.contracts.CalculatorContracts
import com.globant.onboardingcalculatorwithkotlin.mvp.model.CalculatorModel
import com.globant.onboardingcalculatorwithkotlin.mvp.presenter.CalculatorPresenter
import com.globant.onboardingcalculatorwithkotlin.mvp.view.CalculatorView
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_EIGHT
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_FIVE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_FOUR
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_NINE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_ONE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_SEVEN
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_SIX
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_THREE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_TWO
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.NUMBER_ZERO
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_DIVIDE
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_MULTIPLY
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_PLUS
import com.globant.onboardingcalculatorwithkotlin.utils.Constants.OPERATOR_SUBSTRACTION
import kotlinx.android.synthetic.main.activity_main.button_main_clear
import kotlinx.android.synthetic.main.activity_main.button_main_decimal_point
import kotlinx.android.synthetic.main.activity_main.button_main_divide
import kotlinx.android.synthetic.main.activity_main.button_main_multiply
import kotlinx.android.synthetic.main.activity_main.button_main_number_eight
import kotlinx.android.synthetic.main.activity_main.button_main_number_five
import kotlinx.android.synthetic.main.activity_main.button_main_number_four
import kotlinx.android.synthetic.main.activity_main.button_main_number_nine
import kotlinx.android.synthetic.main.activity_main.button_main_number_one
import kotlinx.android.synthetic.main.activity_main.button_main_number_seven
import kotlinx.android.synthetic.main.activity_main.button_main_number_six
import kotlinx.android.synthetic.main.activity_main.button_main_number_three
import kotlinx.android.synthetic.main.activity_main.button_main_number_two
import kotlinx.android.synthetic.main.activity_main.button_main_number_zero
import kotlinx.android.synthetic.main.activity_main.button_main_plus
import kotlinx.android.synthetic.main.activity_main.button_main_substraction

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: CalculatorContracts.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = CalculatorPresenter(CalculatorModel(), CalculatorView(this))
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        button_main_number_zero.setOnClickListener {
            presenter.onNumberPressed(NUMBER_ZERO)
        }
        button_main_number_one.setOnClickListener {
            presenter.onNumberPressed(NUMBER_ONE)
        }
        button_main_number_two.setOnClickListener {
            presenter.onNumberPressed(NUMBER_TWO)
        }
        button_main_number_three.setOnClickListener {
            presenter.onNumberPressed(NUMBER_THREE)
        }
        button_main_number_four.setOnClickListener {
            presenter.onNumberPressed(NUMBER_FOUR)
        }
        button_main_number_five.setOnClickListener {
            presenter.onNumberPressed(NUMBER_FIVE)
        }
        button_main_number_six.setOnClickListener {
            presenter.onNumberPressed(NUMBER_SIX)
        }
        button_main_number_seven.setOnClickListener {
            presenter.onNumberPressed(NUMBER_SEVEN)
        }
        button_main_number_eight.setOnClickListener {
            presenter.onNumberPressed(NUMBER_EIGHT)
        }
        button_main_number_nine.setOnClickListener {
            presenter.onNumberPressed(NUMBER_NINE)
        }
        button_main_plus.setOnClickListener {
            presenter.onOperatorPressed(OPERATOR_PLUS)
        }
        button_main_substraction.setOnClickListener {
            presenter.onOperatorPressed(OPERATOR_SUBSTRACTION)
        }
        button_main_multiply.setOnClickListener {
            presenter.onOperatorPressed(OPERATOR_MULTIPLY)
        }
        button_main_divide.setOnClickListener {
            presenter.onOperatorPressed(OPERATOR_DIVIDE)
        }
        button_main_clear.setOnClickListener {
            presenter.onClearButtonPressed()
        }
        button_main_decimal_point.setOnClickListener {
            presenter.onPointPressed()
        }
    }
}
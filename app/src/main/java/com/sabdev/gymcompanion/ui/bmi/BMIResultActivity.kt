package com.sabdev.gymcompanion.ui.bmi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sabdev.gymcompanion.R

class BMIResultActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDesc: TextView
    private lateinit var butRecalc: Button

    private var result: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiresult)
        if (intent != null && intent.extras != null) {
            result = intent.extras?.getDouble(BMIFragment.IMC_KEY) ?: -1.0
            initComponent()
            initUI()
            initListeners()
        } else {
            finish()
        }
    }


    private fun initComponent() {
        tvIMC = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDesc = findViewById(R.id.tvDesc)
        butRecalc = findViewById(R.id.butRecalc)
    }

    private fun initListeners() {
        butRecalc.setOnClickListener { onBackPressed() }
    }


    private fun initUI() {
        tvIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> { //bajo peso
                tvDesc.text = getString(R.string.low)
                tvResult.text = getString(R.string.descLow)
            }

            in 18.51..24.99 -> { //normal
                tvDesc.text = getString(R.string.nor)
                tvResult.text = getString(R.string.descNor)
            }

            in 25.00..29.99 -> { //sobrepeso
                tvDesc.text = getString(R.string.over)
                tvResult.text = getString(R.string.desOver)
            }

            in 30.00..99.00 -> { //obesidad
                tvDesc.text = getString(R.string.obe)
                tvResult.text = getString(R.string.desObe)
            }

            else -> { //error
                tvIMC.text = "Error"
                tvDesc.text = "Error"
                tvResult.text = "Error"
            }
        }
    }
}
package com.sabdev.gymcompanion.ui.bmi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.sabdev.gymcompanion.databinding.FragmentBMIBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class BMIFragment : Fragment() {


    private var _binding: FragmentBMIBinding? = null
    private val binding get() = _binding!!

    private var currentWeight: Int = 70
    private var currentAge: Int = 30
    private var currentHeight: Int = 120

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBMIBinding.inflate(inflater, container, false)
        initComponents()
        initListeners()
        initUI()
        return binding.root
    }

    private fun initComponents() {
        viewMale = binding.viewMale
        viewFemale = binding.viewFemale
        tvHeight = binding.tvHeight
        rsHeight = binding.rsHeight
        btnSubtractWeight = binding.butSubWeight
        btnPlusWeight = binding.butPlusWeight
        tvWeight = binding.tvweight
        btnSubtractAge = binding.butSubAge
        btnPlusAge = binding.butPlusAge
        tvAge = binding.tvAge
        btnCalculate = binding.butCalc
    }

    private fun initListeners() {
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }
        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(requireContext(), BMIResultActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
        return df.format(imc).toDouble()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun initUI() {
        setWeight()
        setAge()
    }
}


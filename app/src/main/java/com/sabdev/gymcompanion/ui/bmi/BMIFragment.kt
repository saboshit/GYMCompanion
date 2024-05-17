package com.sabdev.gymcompanion.ui.bmi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.sabdev.gymcompanion.R
import com.sabdev.gymcompanion.databinding.FragmentBMIBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class BMIFragment : Fragment() {

    override fun onPause() {
        super.onPause()
        saveData()
    }

    private var _binding: FragmentBMIBinding? = null
    private val binding get() = _binding!!

    private var selectedGender: String = "Male"
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
        rsHeight.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
                // No hacer nada cuando el usuario empieza a arrastrar el thumb del slider
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
                // Guardar los datos cuando el usuario deja de arrastrar el thumb del slider
                val df = DecimalFormat("#.##")
                currentHeight = df.format(slider.values[0]).toInt()
                tvHeight.text = "$currentHeight cm"
                saveData()
            }
        })
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }
        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
            saveData()
        }
        btnSubtractWeight.setOnClickListener {
            if (currentWeight > 0) {
                currentWeight -= 1
                setWeight()
                saveData()
            }
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
            saveData()
        }
        btnSubtractAge.setOnClickListener {
            if (currentAge > 0) {
                currentAge -= 1
                setAge()
                saveData()
            }
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
        viewMale.setOnClickListener {
            selectedGender = "Male"
            viewMale.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.btnTint
                )
            )
            viewFemale.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.backCard
                )
            )
            saveData()
        }
        viewFemale.setOnClickListener {
            selectedGender = "Female"
            viewFemale.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.btnTint
                )
            )
            viewMale.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.backCard
                )
            )
            saveData()
        }

    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(requireContext(), BMIResultActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val heightInMeters = currentHeight.toDouble() / 100
        val imc = currentWeight / (heightInMeters * heightInMeters)
        val imcString = df.format(imc).replace(",", ".")
        return imcString.toDouble()
    }

    private fun saveData() {
        val sharedPreferences =
            this.activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.apply {
            putInt("SAVED_WEIGHT", currentWeight)
            putInt("SAVED_AGE", currentAge)
            putInt("SAVED_HEIGHT", currentHeight)
            putString("SAVED_GENDER", selectedGender)
        }?.apply()
    }

    private fun loadData() {
        val sharedPreferences =
            this.activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        currentWeight = sharedPreferences?.getInt("SAVED_WEIGHT", 70) ?: 70
        currentAge = sharedPreferences?.getInt("SAVED_AGE", 30) ?: 30
        currentHeight = sharedPreferences?.getInt("SAVED_HEIGHT", 120) ?: 120
        selectedGender = sharedPreferences?.getString("SAVED_GENDER", "Male") ?: "Male"

        // Actualizar el valor del RangeSlider
        rsHeight.values = listOf(currentHeight.toFloat())
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun initUI() {
        loadData()
        setWeight()
        setAge()
        if (selectedGender == "Male") {
            viewMale.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.btnTint
                )
            )
            viewFemale.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.backCard
                )
            )
        } else {
            viewFemale.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.btnTint
                )
            )
            viewMale.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.backCard
                )
            )
        }
    }
}


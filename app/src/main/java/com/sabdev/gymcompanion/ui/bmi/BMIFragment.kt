package com.sabdev.gymcompanion.ui.bmi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sabdev.gymcompanion.databinding.FragmentBMIBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BMIFragment : Fragment() {

    private var _binding: FragmentBMIBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBMIBinding.inflate(inflater, container, false)
        return binding.root
    }
}
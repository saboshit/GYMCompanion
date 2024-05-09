package com.sabdev.gymcompanion.ui.routines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sabdev.gymcompanion.databinding.FragmentRoutBinding

class RoutFragment : Fragment() {

    private var _binding: FragmentRoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoutBinding.inflate(inflater, container, false)
        return binding.root
    }
}
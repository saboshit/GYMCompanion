package com.sabdev.gymcompanion.ui.sett

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sabdev.gymcompanion.databinding.FragmentSettBinding

class SettFragment : Fragment() {

    private var _binding: FragmentSettBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettBinding.inflate(inflater, container, false)
        return binding.root
    }
}
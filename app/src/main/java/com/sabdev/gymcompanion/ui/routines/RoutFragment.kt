package com.sabdev.gymcompanion.ui.routines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sabdev.gymcompanion.databinding.FragmentRoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.monCardView.setOnClickListener {
            if (binding.monDescTextView.visibility == View.GONE) {
                binding.monDescTextView.visibility = View.VISIBLE
            } else {
                binding.monDescTextView.visibility = View.GONE
            }
        }
        binding.tueCardView.setOnClickListener {
            if (binding.tueDescTextView.visibility == View.GONE) {
                binding.tueDescTextView.visibility = View.VISIBLE
            } else {
                binding.tueDescTextView.visibility = View.GONE
            }
        }
        binding.wedCardView.setOnClickListener {
            if (binding.wedDescTextView.visibility == View.GONE) {
                binding.wedDescTextView.visibility = View.VISIBLE
            } else {
                binding.wedDescTextView.visibility = View.GONE
            }
        }
        binding.thuCardView.setOnClickListener {
            if (binding.thuDescTextView.visibility == View.GONE) {
                binding.thuDescTextView.visibility = View.VISIBLE
            } else {
                binding.thuDescTextView.visibility = View.GONE
            }
        }
        binding.friCardView.setOnClickListener {
            if (binding.friDescTextView.visibility == View.GONE) {
                binding.friDescTextView.visibility = View.VISIBLE
            } else {
                binding.friDescTextView.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
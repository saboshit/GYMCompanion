package com.sabdev.gymcompanion.ui.routines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.sabdev.gymcompanion.R
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

        val daysOfWeek = listOf(
            Pair(binding.monCardView, binding.monDescTextView),
            Pair(binding.tueCardView, binding.tueDescTextView),
            Pair(binding.wedCardView, binding.wedDescTextView),
            Pair(binding.thuCardView, binding.thuDescTextView),
            Pair(binding.friCardView, binding.friDescTextView)
        )

        for ((cardView, descTextView) in daysOfWeek) {
            cardView.setOnClickListener {
                if (descTextView.visibility == View.GONE) {
                    descTextView.visibility = View.VISIBLE
                } else {
                    descTextView.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
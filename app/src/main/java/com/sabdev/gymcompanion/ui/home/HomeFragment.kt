package com.sabdev.gymcompanion.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sabdev.gymcompanion.R
import com.sabdev.gymcompanion.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")

        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        binding.tvGreeting.text = when (currentHour) {
            in 0..11 -> getString(R.string.gm, username)
            in 12..17 -> getString(R.string.ga, username)
            else -> getString(R.string.ge, username)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
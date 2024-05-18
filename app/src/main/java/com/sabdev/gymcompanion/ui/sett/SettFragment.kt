package com.sabdev.gymcompanion.ui.sett

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sabdev.gymcompanion.LocaleHelper
import com.sabdev.gymcompanion.R
import com.sabdev.gymcompanion.databinding.FragmentSettBinding
import com.sabdev.gymcompanion.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class SettFragment : Fragment() {

    private lateinit var btnGit: FloatingActionButton
    private lateinit var btnShare: FloatingActionButton
    private lateinit var btnAbout: FloatingActionButton
    private lateinit var btnLanguage: FloatingActionButton
    private lateinit var btnTheme: FloatingActionButton

    private val languages = listOf("English", "Español")

    private var _binding: FragmentSettBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettBinding.inflate(inflater, container, false)
        initComponents()
        initListeners()

        return binding.root
    }


    private fun initComponents() {
        btnGit = binding.btnGit
        btnShare = binding.btnShare
        btnAbout = binding.btnAbout
        btnLanguage = binding.btnLanguage
        btnTheme = binding.btnTheme
    }

    private fun isNightModeActive(): Boolean {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return false
        return sharedPref.getBoolean("NIGHT_MODE", false)
    }

    private fun setNightMode(isNightMode: Boolean) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean("NIGHT_MODE", isNightMode)
            apply()
        }
    }

    private fun initListeners() {
        btnGit.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/saboshit/GYMCompanion"))
            startActivity(browserIntent)
        }
        btnAbout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage(getString(R.string.abt))
                .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                })
            builder.create().show()
        }
        btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    getString(R.string.shareApp) + "\nhttps://github.com/saboshit/GYMCompanion"
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
        btnLanguage.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(getString(R.string.selectLang))
                .setItems(languages.toTypedArray()) { dialog, which ->
                    when (which) {
                        0 -> {
                            // Set the language to English
                            LocaleHelper.setLocale(requireContext(), Locale.ENGLISH.toString())
                        }

                        1 -> {
                            // Set the language to Spanish
                            LocaleHelper.setLocale(requireContext(), Locale("es").toString())
                        }
                    }
                    dialog.dismiss()

                    // Clear the back stack and start a new MainActivity
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            builder.create().show()
        }
        btnTheme.setOnClickListener {
            (activity as MainActivity).toggleTheme()
            if (isNightModeActive()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                setNightMode(false)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                setNightMode(true)
            }
        }
    }
}
package com.sabdev.gymcompanion.ui.home

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sabdev.gymcompanion.LocaleHelper
import com.sabdev.gymcompanion.R
import com.sabdev.gymcompanion.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getThemePreference()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    fun toggleTheme() {
        val isDarkMode = getThemePreference()
        setThemePreference(!isDarkMode)
        if (!isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun initUI() {
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }

    fun setThemePreference(isDarkMode: Boolean) {
        val sharedPref = getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("isDarkMode", isDarkMode)
            apply()
        }
    }

    fun getThemePreference(): Boolean {
        val sharedPref = getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("isDarkMode", false)
    }
}
package com.example.games

import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.games.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == com.example.games.home.R.id.gameDetailsFragment) {
                hideBottomNav()
            } else {
                showBottomNav()
            }
        }
    }

    private fun showBottomNav(duration: Int = 400) {
        if (binding.bottomNav.visibility == View.VISIBLE) return
        binding.bottomNav.visibility = View.VISIBLE
        val animate = TranslateAnimation(0f, 0f, binding.bottomNav.height.toFloat(), 0f)
        animate.duration = duration.toLong()
        binding.bottomNav.startAnimation(animate)
    }

    private fun hideBottomNav(duration: Int = 400) {
        if (binding.bottomNav.visibility == View.GONE) return
        val animate = TranslateAnimation(0f, 0f, 0f, binding.bottomNav.height.toFloat())
        animate.duration = duration.toLong()
        binding.bottomNav.startAnimation(animate)
        binding.bottomNav.visibility = View.GONE
    }
}
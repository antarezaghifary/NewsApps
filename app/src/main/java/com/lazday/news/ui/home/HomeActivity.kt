package com.lazday.news.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lazday.news.R
import com.lazday.news.databinding.ActivityHomeBinding
import timber.log.Timber

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Timber.e("try home")

        //todo 5
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)

    }
}
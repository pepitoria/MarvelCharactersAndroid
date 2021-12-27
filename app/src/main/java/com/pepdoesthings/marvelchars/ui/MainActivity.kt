package com.pepdoesthings.marvelchars.ui

import android.content.Intent
import android.os.Bundle
import com.pepdoesthings.marvelchars.databinding.ActivityMainBinding
import com.pepdoesthings.marvelchars.ui.base.BaseActivity
import com.pepdoesthings.marvelchars.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toHomeScreenButton.setOnClickListener {
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
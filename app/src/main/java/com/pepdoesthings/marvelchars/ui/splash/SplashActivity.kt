package com.pepdoesthings.marvelchars.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacters
import com.pepdoesthings.marvelchars.ui.base.BaseActivity
import com.pepdoesthings.marvelchars.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.marvelCharacters.observe(this, Observer { marvelCharacters ->
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            intent.putExtra(MarvelCharacters.KEY, marvelCharacters)
            startActivity(intent)
            finish()
        })

        viewModel.getChars()

    }
}
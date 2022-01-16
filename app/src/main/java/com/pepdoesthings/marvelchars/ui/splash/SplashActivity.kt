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
        //the view of this splash activity is handled by the theme background


        viewModel.marvelCharacters.observe(this, Observer { marvelCharacters ->
            HomeActivity.launch(this@SplashActivity, marvelCharacters)
            finish()
        })

        viewModel.getChars()

    }
}
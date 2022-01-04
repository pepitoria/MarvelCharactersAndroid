package com.pepdoesthings.marvelchars.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pepdoesthings.marvelchars.R
import com.pepdoesthings.marvelchars.databinding.HomeActivityBinding
import com.pepdoesthings.marvelchars.ui.base.BaseActivity
import com.pepdoesthings.marvelchars.ui.characterlist.CharacterListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CharacterListFragment.newInstance())
                .commitNow()
        }
    }
}
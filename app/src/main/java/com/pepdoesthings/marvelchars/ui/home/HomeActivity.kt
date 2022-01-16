package com.pepdoesthings.marvelchars.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pepdoesthings.marvelchars.R
import com.pepdoesthings.marvelchars.databinding.HomeActivityBinding
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacters
import com.pepdoesthings.marvelchars.ui.base.BaseActivity
import com.pepdoesthings.marvelchars.ui.characterlist.CharacterListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    companion object {
        fun launch(context: Context, marvelCharacters: MarvelCharacters) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(MarvelCharacters.KEY, marvelCharacters)
            context.startActivity(intent)
        }

        fun launch(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }

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
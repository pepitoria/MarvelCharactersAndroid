package com.pepdoesthings.marvelchars.ui.characterlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pepdoesthings.marvelchars.R
import com.pepdoesthings.marvelchars.databinding.CharacterListFragmentBinding
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacter
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacters
import com.pepdoesthings.marvelchars.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CharacterListFragment : BaseFragment() {

    companion object {
        fun newInstance() = CharacterListFragment()
    }

    private lateinit var binding: CharacterListFragmentBinding
    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        viewModel.getChars()
    }

    fun setupViews() {
        val charactersAdapter =
            CharactersAdapter { character -> adapterOnClick(character) }
        binding.charactersRecyclerView.adapter = charactersAdapter
        binding.charactersRecyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.isLoading.observe(this, Observer { loading ->
            if (loading) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        })

        viewModel.marvelCharacters.observe(this, Observer { marvelCharacters ->
            charactersAdapter.addCharacters(marvelCharacters)
        })
    }

    private fun adapterOnClick(character: MarvelCharacter) {
        Timber.d("adapterOnClick: $character")
    }

}


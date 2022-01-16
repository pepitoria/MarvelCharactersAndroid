package com.pepdoesthings.marvelchars.ui.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pepdoesthings.marvelchars.R
import com.pepdoesthings.marvelchars.databinding.CharacterListFragmentBinding
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacter
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacters
import com.pepdoesthings.marvelchars.ui.base.BaseFragment
import com.pepdoesthings.marvelchars.ui.characterdetail.CharacterDetailFragment
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

        val firstCall = activity?.intent?.getParcelableExtra<MarvelCharacters>(MarvelCharacters.KEY)

        if (firstCall != null) {
            viewModel.setFirstCall(firstCall)
        } else {
            viewModel.getChars("")
        }

    }

    private fun setupViews() {
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
        viewModel.apiError.observe(this, Observer { error ->
            // here we would just let the user know there has been an error. Design wise, it could be better
            binding.errorMessage.visibility = View.VISIBLE
            binding.errorMessage.text =
                getString(R.string.home_error, error.code.toString(), error.msg)
            binding.charactersRecyclerView.visibility = View.GONE

        })
        viewModel.marvelCharacters.observe(this, Observer { marvelCharacters ->
            if (marvelCharacters.allCharacters.isEmpty()) {
                binding.errorMessage.visibility = View.VISIBLE
                binding.charactersRecyclerView.visibility = View.GONE
                binding.errorMessage.text =
                    getString(R.string.no_results, binding.searchBar.text.toString())

            } else {
                binding.errorMessage.visibility = View.GONE
                binding.charactersRecyclerView.visibility = View.VISIBLE
                charactersAdapter.showCharacters(marvelCharacters)
            }
        })

        binding.searchBar.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getChars(binding.searchBar.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun adapterOnClick(character: MarvelCharacter) {
        Timber.d("adapterOnClick: $character")

        val detail = CharacterDetailFragment.newInstance(character.id)
        showDetail(detail, detail.getCharacterTag())
    }

    private fun showDetail(
        fragment: Fragment,
        fragmentTag: String
    ) {
        activity?.let {
            it.supportFragmentManager
                .beginTransaction()
                .add(R.id.detail, fragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit()
        }
    }
}


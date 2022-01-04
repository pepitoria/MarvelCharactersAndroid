package com.pepdoesthings.marvelchars.ui.characterdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.pepdoesthings.marvelchars.R
import com.pepdoesthings.marvelchars.data.model.CharactersResponse
import com.pepdoesthings.marvelchars.data.model.Result
import com.pepdoesthings.marvelchars.databinding.CharacterDetailFragmentBinding
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacters
import com.pepdoesthings.marvelchars.ui.base.BaseFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    companion object {
        private const val CHAR_ID = "CHAR_ID"

        @JvmStatic
        fun newInstance(charId: Long) =
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putLong(CHAR_ID, charId)
                }
            }
    }

    private var charId: Long = 0
    private lateinit var binding: CharacterDetailFragmentBinding
    private val viewModel: CharacterDetailViewModel by viewModels()
    var prevTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            charId = it.getLong(CHAR_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterDetailFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prevTitle = activity?.title.toString()

        viewModel.isLoading.observe(this, Observer { loading ->
            if (loading) {
                binding.loading.visibility = View.VISIBLE
                activity?.title = getString(R.string.loading)
            } else {
                binding.loading.visibility = View.GONE
            }
        })

        viewModel.marvelCharacter.observe(this, Observer { marvelCharacter ->
            showCharacter(marvelCharacter)
        })

        viewModel.getChar(charId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.title = prevTitle
    }

    private fun showCharacter(response: MarvelCharacters) {
        Timber.d(response.toString())

        // check if data would ever come empty. proper error message would be nice.
        if (response.allCharacters.isEmpty()) {
            activity?.let {
                // we go back
                it.supportFragmentManager.popBackStack()
                // and stop this method.
                return
            }
        }

        val result = response.allCharacters[0]


        // we set the title of the activity and unset it on the onDestroy method
        activity?.title = result.name

        binding.detailDescription.text = result.description
        binding.detailComics.text = getString(R.string.character_comics, result.name)

        Picasso.get()
            .load(result.image)
            .placeholder(R.mipmap.no_image_placeholder)
            .fit()
            .into(binding.detailImage)

        binding.detailKnowMore.setOnClickListener {
            viewModel.openURL(result.knowMoreUrl, context)
        }

        binding.detailComics.setOnClickListener {
            viewModel.openURL(result.comicsUrl, context)
        }
    }

    fun getCharacterTag(): String {
        return "CharacterDetailFrament#${charId}"
    }

}
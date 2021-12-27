package com.pepdoesthings.marvelchars.ui.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pepdoesthings.marvelchars.databinding.CharacterListFragmentBinding
import com.pepdoesthings.marvelchars.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

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
    }

    fun setupViews() {
        binding.characterList.setOnClickListener {
            viewModel.getChars()
        }
    }

}
package com.pepdoesthings.marvelchars.ui.characterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pepdoesthings.marvelchars.R
import com.pepdoesthings.marvelchars.databinding.CharacterListItemBinding
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacter
import com.pepdoesthings.marvelchars.domain.model.MarvelCharacters
import com.squareup.picasso.Picasso
import timber.log.Timber

class CharactersAdapter(
    private val onClick: (MarvelCharacter) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.MarvelCharacterViewHolder>() {

    private val charList: MutableList<MarvelCharacter> = mutableListOf()

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // View holder
    class MarvelCharacterViewHolder(
        private val binding: CharacterListItemBinding,
        val onClick: (MarvelCharacter) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(
                parent: ViewGroup,
                viewType: Int,
                onClick: (MarvelCharacter) -> Unit
            ): MarvelCharacterViewHolder {
                val binding = CharacterListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return MarvelCharacterViewHolder(binding, onClick)
            }
        }

        private var character: MarvelCharacter? = null

        init {
            itemView.setOnClickListener {
                character?.let {
                    onClick(it)
                }
            }
        }

        fun bind(char: MarvelCharacter) {
            this.character = char

            Timber.d("binding char: ${char.name} with thumbnail ${char.thumbnail}")
            binding.characterName.text = char.name

            Picasso.get()
                .load(char.thumbnail)
                .placeholder(R.mipmap.no_image_placeholder)
                .fit()
                .into(binding.thumbnail)

        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        return MarvelCharacterViewHolder.create(parent, viewType, onClick)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        val character = charList[position]
        holder.bind(character)
    }

    fun showCharacters(marvelCharacters: MarvelCharacters) {
        charList.clear()
        charList.addAll(marvelCharacters.allCharacters)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return charList.size
    }

}
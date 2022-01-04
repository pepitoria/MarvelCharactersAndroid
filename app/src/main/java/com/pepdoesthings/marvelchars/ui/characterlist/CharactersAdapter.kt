package com.pepdoesthings.marvelchars.ui.characterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pepdoesthings.marvelchars.R
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
    class MarvelCharacterViewHolder(itemView: View, val onClick: (MarvelCharacter) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        private val name: TextView = itemView.findViewById(R.id.characterName)
        private val character: MarvelCharacter? = null

        init {
            itemView.setOnClickListener {
                character?.let {
                    onClick(it)
                }
            }
        }

        fun bind(character: MarvelCharacter) {
            Timber.d("binding char: ${character.name} with thumbnail ${character.thumbnail}")
            name.text = character.name

            Picasso.get()
                .load(character.thumbnail)
                .placeholder(R.mipmap.no_image_placeholder)
                .fit()
                .into(thumbnail)

        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_item, parent, false)
        return MarvelCharacterViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        val character = charList[position]
        holder.bind(character)
    }

    fun addCharacters(marvelCharacters: MarvelCharacters) {
        charList.addAll(marvelCharacters.allCharacters)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return charList.size
    }

}
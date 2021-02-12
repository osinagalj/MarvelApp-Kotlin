package com.intermedia.challenge.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.ui.base.BaseAdapter
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.R
import com.intermedia.challenge.databinding.ViewHeroItemBinding

class CharactersAdapter : BaseAdapter<Character, CharactersAdapter.CharactersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        CharactersViewHolder(
            ViewHeroItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_hero_item,
                    parent,
                    false
                )
            ), onClickListener
        )

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class CharactersViewHolder(
        private val binding: ViewHeroItemBinding,
        private val onClickListener: ((Character) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character) = with(itemView) {
            binding.character = item
            binding.root.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }
    }
}
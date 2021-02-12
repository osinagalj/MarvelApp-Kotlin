package com.intermedia.challenge.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Appearance
import com.intermedia.challenge.databinding.ViewHeroDetailsComicsBinding

import com.intermedia.challenge.ui.base.BaseAdapter

class ComicAdapter: BaseAdapter<Appearance, ComicAdapter.ComicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder =
        ComicsViewHolder(
            ViewHeroDetailsComicsBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_hero_details_comics,
                    parent,
                    false
                )
            )
        )

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(list[position])
        holder.setIsRecyclable(false)
    }

    class ComicsViewHolder(
        private val binding: ViewHeroDetailsComicsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Appearance) = with(itemView) {

            binding.textComic.text = item.name
            binding.textComicYear.text = "1990" // que dato usar?????????

        }

    }
}
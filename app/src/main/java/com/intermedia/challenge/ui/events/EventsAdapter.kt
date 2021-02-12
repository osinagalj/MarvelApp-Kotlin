package com.intermedia.challenge.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.databinding.ViewEventItemBinding
import com.intermedia.challenge.ui.base.BaseAdapter


class EventsAdapter : BaseAdapter<Event, EventsAdapter.EventsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder =
        EventsViewHolder(
            ViewEventItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_event_item,
                    parent,
                    false
                )
            ), onClickListener
        )

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class EventsViewHolder(private val binding: ViewEventItemBinding, private val onClickListener: ((Event) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = ComicAdapter()
        fun bind(item: Event) = with(itemView) {
            binding.event = item
            binding.listComics.adapter = adapter

            //add the comics to the adapter
            adapter.addAll(item.comics.appearances)

            //hide recycler view
            binding.layoutComics.visibility = View.GONE

            /**
             * A logic could be made so that the button does not appear if it does not have comics to discuss,
             * and that the text of comics to be discussed is also hidden
             */
            //expand & collapse
            binding.ivExpand.setOnClickListener(){
                if(binding.layoutComics.visibility == View.VISIBLE){
                    binding.layoutComics.visibility = View.GONE
                    binding.ivExpand.setImageResource(R.drawable.ic_expand_more)
                }else{
                    binding.layoutComics.visibility = View.VISIBLE
                    binding.ivExpand.setImageResource(R.drawable.ic_expand_less)
                }
            }

            binding.root.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }
    }
}
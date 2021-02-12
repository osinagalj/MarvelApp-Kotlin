package com.intermedia.challenge.ui.events

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intermedia.challenge.databinding.FragmentEventsBinding

import org.koin.android.viewmodel.ext.android.viewModel


class EventsFragment : Fragment( ) {

    private lateinit var binding: FragmentEventsBinding
    private val viewModel: EventsViewModel by viewModel()
    private val adapter = EventsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEventList()
    }

    private fun setupEventList() {
        binding.listEvents.adapter = adapter
        viewModel.events.observe(viewLifecycleOwner, { events ->
            adapter.addAll(events)
        })
    }
}


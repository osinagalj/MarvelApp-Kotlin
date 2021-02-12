package com.intermedia.challenge.ui.characters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.databinding.FragmentCharactersBinding
import com.intermedia.challenge.ui.characterdDetails.CharacterActivity
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModel()
    private val adapter = CharactersAdapter()

    //private lateinit var viewModel: CharactersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

       // viewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCharactersList()
        setupPagination()
    }

    private fun setupPagination() {
        binding.listCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMoreCharacters()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }


    private fun setupCharactersList() {
        adapter.onClickListener = { character ->
            activity?.let{
                val intent = Intent (it, CharacterActivity::class.java)
                intent.putExtra("character_id",character.id.toString())
                it.startActivity(intent)
            }
        }
        binding.listCharacters.adapter = adapter
        viewModel.characters.observe(viewLifecycleOwner, { characters ->
            adapter.addAll(characters)
        })
    }
}



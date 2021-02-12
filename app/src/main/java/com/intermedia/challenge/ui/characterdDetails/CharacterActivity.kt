package com.intermedia.challenge.ui.characterdDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.databinding.ActivityCharacterBinding
import com.bumptech.glide.Glide
import com.intermedia.challenge.ui.events.ComicAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding
    private val viewModel: CharacterDetailsViewModel by viewModel()
    private val adapter = ComicAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //Binding this with the layout Activity_character using data binding
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Getting the id of the character
        val id = getIntent().getStringExtra("character_id")

        //Getting the character by Id using coroutines and retrofit
        viewModel.loadCharacter(id).observe(this, Observer { character ->
            setCharacterData(character)
        })
        binding.buttonExitDetails.setOnClickListener { finish() }

    }

    private fun setCharacterData(character: Character){

       //Set Name and Description
        binding.heroDetailNameTxt.text = character.name
        binding.heroDetailDesc.text = character.description

        //Set image
        val url = "${character.thumbnail.path}.${character.thumbnail.extension}".replace("http", "https")
        Glide.with(this)
            .load(url)
            .placeholder(android.R.color.transparent)
            .into(binding.heroDetailImg)

        //List of Comics
        binding.listComics.adapter = adapter
        viewModel.comics.observe(this, { comics ->
            adapter.addAll(comics)
        })




    }

}
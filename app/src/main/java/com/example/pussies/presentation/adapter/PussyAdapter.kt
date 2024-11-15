package com.example.pussies.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.pussies.databinding.ItemFavoritePussyBinding
import com.example.pussies.domain.Pussy

class PussyAdapter(
    private val onDeleteClick: (pussyId: String) -> Unit
) : ListAdapter<Pussy, PussyViewHolder>(PussyDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PussyViewHolder {
        val binding = ItemFavoritePussyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PussyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PussyViewHolder, position: Int) {
        val pussy = getItem(position)
        Log.d("PussyAdapter", "Binding item at position $position: $pussy")
        with(holder.binding) {
            pussyImage.load(pussy.imageUrl)
            pussyBreed.text = pussy.breedName
            removeFavorite.setOnClickListener {
                onDeleteClick(pussy.id)
            }
        }
    }
}
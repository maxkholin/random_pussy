package com.example.pussies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.pussies.databinding.ItemFavoritePussyBinding
import com.example.pussies.domain.Pussy

class PussyAdapter(
    private val onDeleteClick: (pussyId: String) -> Unit,
    private val onImageClick: ((pussy: Pussy) -> Unit)
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
        with(holder.binding) {
            pussyImage.load(pussy.imageUrl)
            pussyBreed.text = pussy.breedName
            removeFavorite.setOnClickListener {
                onDeleteClick(pussy.id)
            }
            pussyImage.setOnClickListener {
                onImageClick(pussy)
            }
        }
    }
}
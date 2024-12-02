package com.example.pussies.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.pussies.R
import com.example.pussies.base.domain.Pussy
import com.example.pussies.databinding.CardPussyBinding

class PussyAdapter(
    private val onDeleteClick: (pussyId: String) -> Unit,
    private val onImageClick: ((pussy: Pussy) -> Unit)
) : ListAdapter<Pussy, PussyViewHolder>(PussyDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PussyViewHolder {
        val binding = CardPussyBinding.inflate(
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
            toggleFavorite.setImageResource(R.drawable.ic_broken_heart)
            toggleFavorite.setOnClickListener {
                onDeleteClick(pussy.id)
            }
            pussyImage.setOnClickListener {
                onImageClick(pussy)
            }
        }
    }
}
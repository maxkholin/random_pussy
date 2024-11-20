package com.example.pussies.favorites.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pussies.base.domain.Pussy

object PussyDiffCallback : DiffUtil.ItemCallback<Pussy>() {
    override fun areItemsTheSame(oldItem: Pussy, newItem: Pussy): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pussy, newItem: Pussy): Boolean {
        return oldItem == newItem
    }
}
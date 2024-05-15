package com.sabdev.gymcompanion.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sabdev.gymcompanion.databinding.ItemRoutinesBinding
import com.sabdev.gymcompanion.domain.model.RoutInfo

class HomeViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemRoutinesBinding.bind(view)

    fun render(routInfo: RoutInfo) {
        binding.ivRoutine.setImageResource(routInfo.image)
    }
}
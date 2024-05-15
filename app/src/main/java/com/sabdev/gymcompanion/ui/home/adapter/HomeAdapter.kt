package com.sabdev.gymcompanion.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sabdev.gymcompanion.R
import com.sabdev.gymcompanion.domain.model.RoutInfo

class HomeAdapter(private var routList: List<RoutInfo> = emptyList()) : RecyclerView.Adapter<HomeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_routines, parent, false)
        )
    }

    override fun getItemCount() = routList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.render(routList[position])
    }
}
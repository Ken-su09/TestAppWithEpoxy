package com.suonk.testappwithepoxy.ui.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suonk.testappwithepoxy.databinding.ItemAttractionBinding
import com.suonk.testappwithepoxy.models.data.Attraction

class AttractionAdapter(
    private val activity: Activity,
    private val onClickedCallback: (String, Int) -> Unit
) :
    ListAdapter<Attraction, AttractionAdapter.ViewHolder>(AttractionComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemAttractionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val attraction = getItem(position)
        holder.onBind(attraction, onClickedCallback, position)
    }

    inner class ViewHolder(private val binding: ItemAttractionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(attraction: Attraction, onClicked: (String, Int) -> Unit, position: Int) {
            binding.titleTextView.text = attraction.title
            binding.monthsToVisitTextView.text = attraction.months_to_visit

            Glide.with(activity)
                .load(attraction.image_urls[0])
                .centerCrop()
                .into(binding.headerImageView)

            binding.root.setOnClickListener {
                onClicked(attraction.id, position)
            }
        }
    }

    class AttractionComparator : DiffUtil.ItemCallback<Attraction>() {
        override fun areItemsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem.description == newItem.description &&
                    oldItem.facts == newItem.facts &&
                    oldItem.id == newItem.id &&
                    oldItem.image_urls == newItem.image_urls &&
                    oldItem.location == newItem.location &&
                    oldItem.title == newItem.title
        }
    }
}
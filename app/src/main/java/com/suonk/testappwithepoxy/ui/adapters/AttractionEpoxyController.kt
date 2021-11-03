package com.suonk.testappwithepoxy.ui.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.bumptech.glide.Glide
import com.suonk.testappwithepoxy.R
import com.suonk.testappwithepoxy.databinding.ItemAttractionBinding
import com.suonk.testappwithepoxy.models.data.Attraction
import com.suonk.testappwithepoxy.ui.epoxy.ViewBindingKotlinModel

class AttractionEpoxyController(
    private val activity: Activity,
    private val onClickedCallback: (String, Int) -> Unit
) : EpoxyController() {

    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var attractions = ArrayList<Attraction>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    override fun buildModels() {
        if (isLoading) {
            return
        }

        if (attractions.isEmpty()) {
            return
        }

        attractions.forEach { attraction ->
            AttractionEpoxyModel(attraction, onClickedCallback, activity)
                .id(attraction.id)
                .addTo(this)
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

    data class AttractionEpoxyModel(
        private val attraction: Attraction,
        private val onClicked: (String, Int) -> Unit,
        private val activity: Activity
    ) : ViewBindingKotlinModel<ItemAttractionBinding>(R.layout.item_attraction) {

        override fun ItemAttractionBinding.bind() {
            titleTextView.text = attraction.title
            monthsToVisitTextView.text = attraction.months_to_visit

            Glide.with(activity)
                .load(attraction.image_urls[0])
                .centerCrop()
                .into(headerImageView)

            root.setOnClickListener {
//                onClicked(attraction.id, position)
            }
        }
    }
}
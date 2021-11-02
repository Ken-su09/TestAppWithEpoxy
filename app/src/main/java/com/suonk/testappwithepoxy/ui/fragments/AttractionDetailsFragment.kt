package com.suonk.testappwithepoxy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.suonk.testappwithepoxy.R
import com.suonk.testappwithepoxy.databinding.FragmentAttractionDetailsBinding
import com.suonk.testappwithepoxy.models.data.Attraction
import com.suonk.testappwithepoxy.ui.activity.MainActivity
import com.suonk.testappwithepoxy.viewmodels.AttractionsViewModel

class AttractionDetailsFragment : BaseFragment() {

    private var binding: FragmentAttractionDetailsBinding? = null
    private lateinit var contextActivity: MainActivity

    private val viewModel: AttractionsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttractionDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contextActivity = activity as MainActivity

        viewModel.attractionLiveData.observe(viewLifecycleOwner, { attraction ->
            binding?.apply {
                headerImageView.let {
                    Glide.with(contextActivity)
                        .load(attraction.image_urls[0])
                        .centerCrop()
                        .into(it)
                }
                titleTextView.text = attraction.title
                descriptionTextView.text = attraction.description
                monthsToVisitTextView.text = attraction.months_to_visit
                numberOfFactsTextView.text = "${attraction.facts.size} facts"
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
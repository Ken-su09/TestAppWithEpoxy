package com.suonk.testappwithepoxy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.suonk.testappwithepoxy.R
import com.suonk.testappwithepoxy.databinding.FragmentAttractionDetailsBinding
import com.suonk.testappwithepoxy.models.data.Attraction
import com.suonk.testappwithepoxy.ui.activity.MainActivity

class AttractionDetailsFragment : BaseFragment() {

    private var binding: FragmentAttractionDetailsBinding? = null
    private val safeArgs: AttractionDetailsFragmentArgs by navArgs()
    private lateinit var contextActivity: MainActivity

    private val attraction: Attraction? by lazy {
        attractions?.find { it.id == safeArgs.attractionId }
    }

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

        binding?.apply {
            headerImageView.let {
                Glide.with(contextActivity)
                    .load(attraction?.image_urls?.get(0))
                    .centerCrop()
                    .into(it)
            }
            titleTextView.text = attraction?.title
            descriptionTextView.text = attraction?.description
            monthsToVisitTextView.text = attraction?.months_to_visit
            numberOfFactsTextView.text = attraction?.facts?.size.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
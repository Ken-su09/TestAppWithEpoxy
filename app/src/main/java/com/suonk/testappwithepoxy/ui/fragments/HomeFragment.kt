package com.suonk.testappwithepoxy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suonk.testappwithepoxy.databinding.FragmentHomeBinding
import com.suonk.testappwithepoxy.ui.activity.MainActivity
import com.suonk.testappwithepoxy.ui.adapters.AttractionEpoxyController
import com.suonk.testappwithepoxy.viewmodels.AttractionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var binding: FragmentHomeBinding? = null

    private lateinit var epoxyController: AttractionEpoxyController
    private lateinit var contextActivity: MainActivity

    private val viewModel: AttractionsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contextActivity = activity as MainActivity
        navigateToAttractionDetails()
        initRecyclerView()
    }

    private fun navigateToAttractionDetails() {
        epoxyController = AttractionEpoxyController(contextActivity) { id, position ->
            val navDir = HomeFragmentDirections.actionHomeFragmentToAttractionDetailFragment(id)
            navController.navigate(navDir)

            viewModel.attractionsListLiveData.observe(viewLifecycleOwner, { attractions ->
                viewModel.setAttractionLiveData(attractions[position])
            })
        }
    }

    private fun initAttractionsList() {
        viewModel.parseAttractions(contextActivity)
        viewModel.attractionsListLiveData.observe(viewLifecycleOwner, { attractions ->
            epoxyController.attractions = attractions
        })
    }

    private fun initRecyclerView() {
        binding?.epoxyRecyclerView?.apply {
            setController(epoxyController)
            adapter = epoxyController.adapter
            initAttractionsList()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(contextActivity)
            addItemDecoration(DividerItemDecoration(contextActivity, RecyclerView.VERTICAL))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
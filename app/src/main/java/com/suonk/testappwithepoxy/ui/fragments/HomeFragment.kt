package com.suonk.testappwithepoxy.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suonk.testappwithepoxy.R
import com.suonk.testappwithepoxy.databinding.FragmentHomeBinding
import com.suonk.testappwithepoxy.ui.activity.MainActivity
import com.suonk.testappwithepoxy.ui.adapters.AttractionAdapter

class HomeFragment : BaseFragment() {

    private var binding: FragmentHomeBinding? = null

    private lateinit var attractionAdapter: AttractionAdapter
    private lateinit var contextActivity: MainActivity

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
        attractionAdapter = AttractionAdapter(contextActivity)
        initRecyclerView()
    }

    private fun navigateToAttractionDetails(){
    }

    private fun initRecyclerView() {
        binding?.recyclerView?.apply {
            adapter = attractionAdapter
            getListAttractionsFromJSONFile()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(contextActivity)
            addItemDecoration(DividerItemDecoration(contextActivity, RecyclerView.VERTICAL))
        }
    }

    private fun getListAttractionsFromJSONFile() {
        Log.i("Attractions", "$attractions")
        attractionAdapter.submitList(null)
        attractionAdapter.submitList(attractions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
package com.suonk.testappwithepoxy.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suonk.testappwithepoxy.models.data.Attraction
import com.suonk.testappwithepoxy.repositories.AttractionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(private val repository: AttractionsRepository) :
    ViewModel() {

    val attractionsListLiveData = MutableLiveData<ArrayList<Attraction>>()

    fun parseAttractions(context: Context) = viewModelScope.launch {
        attractionsListLiveData.postValue(repository.parseAttractions(context))
    }

    val attractionLiveData = MutableLiveData<Attraction>()
    fun setAttractionLiveData(attraction: Attraction) {
        attractionLiveData.postValue(attraction)
    }
}
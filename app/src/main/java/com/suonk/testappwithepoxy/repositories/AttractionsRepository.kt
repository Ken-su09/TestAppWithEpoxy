package com.suonk.testappwithepoxy.repositories

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.suonk.testappwithepoxy.R
import com.suonk.testappwithepoxy.models.data.Attraction
import com.suonk.testappwithepoxy.models.data.AttractionsResponse

class AttractionsRepository {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun parseAttractions(context: Context): ArrayList<Attraction>? {
        val textFromFile = context.resources.openRawResource(R.raw.croatia).bufferedReader().use {
            it.readText()
        }

        val adapter: JsonAdapter<AttractionsResponse> =
            moshi.adapter(AttractionsResponse::class.java)
        return adapter.fromJson(textFromFile)?.attractions as ArrayList<Attraction>?
    }
}
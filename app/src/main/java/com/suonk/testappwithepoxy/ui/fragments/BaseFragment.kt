package com.suonk.testappwithepoxy.ui.fragments

import androidx.fragment.app.Fragment
import com.suonk.testappwithepoxy.models.data.Attraction
import com.suonk.testappwithepoxy.ui.activity.MainActivity

abstract class BaseFragment : Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }
}
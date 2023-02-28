package com.hk.news.core.ui

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.safeNavigate(direction: NavDirections): Boolean {
    return kotlin.runCatching {
        findNavController().navigate(direction)
    }.isSuccess
}
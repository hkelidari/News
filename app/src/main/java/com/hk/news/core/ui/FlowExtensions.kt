package com.hk.news.core.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.collectOn(
    coroutineScope: CoroutineScope,
    crossinline onCollected: (T) -> Unit
) {
    coroutineScope.launch {
        this@collectOn.collect {
            onCollected(it)
        }
    }
}

inline fun <T> Flow<T>.collectOn(
    lifecycleOwner: LifecycleOwner,
    crossinline onCollected: (T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        this@collectOn
            .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .collect { onCollected(it) }
    }
}
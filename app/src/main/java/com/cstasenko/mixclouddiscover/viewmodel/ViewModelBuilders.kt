package com.cstasenko.mixclouddiscover.viewmodel

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider

/**
 * Get a [ViewModel] in a [Fragment] scoped to the lifecycle of that [Fragment].
 */
@MainThread
inline fun <reified VM : ViewModel> Fragment.viewModelBuilderFragmentScope(
    noinline viewModelInitializer: () -> VM
): Lazy<VM> {
    return ViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { viewModelStore },
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST") // Casting T as ViewModel
                    return viewModelInitializer.invoke() as T
                }
            }
        }
    )
}
package com.cstasenko.mixclouddiscover.di

import androidx.fragment.app.Fragment
import com.cstasenko.mixclouddiscover.di.ApplicationModule
import com.cstasenko.mixclouddiscover.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    fun inject(fragment: HomeFragment)
}
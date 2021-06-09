package com.cstasenko.mixclouddiscover.di

import com.cstasenko.mixclouddiscover.view.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    fun inject(fragment: HomeFragment)
}
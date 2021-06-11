package com.cstasenko.mixclouddiscover.di

import com.cstasenko.mixclouddiscover.view.DemoFragment
import com.cstasenko.mixclouddiscover.view.DiscoverFragment
import com.cstasenko.mixclouddiscover.view.UserShowsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    fun inject(fragment: DemoFragment)
    fun inject(fragment: DiscoverFragment)
    fun inject(fragment: UserShowsFragment)
}

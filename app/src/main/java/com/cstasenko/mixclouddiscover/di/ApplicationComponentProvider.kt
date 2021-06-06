package com.cstasenko.mixclouddiscover.di

import com.cstasenko.mixclouddiscover.ApplicationComponent

interface ApplicationComponentProvider {
    fun provideApplicationComponent(): ApplicationComponent
}
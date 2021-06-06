package com.cstasenko.mixclouddiscover

import android.app.Application
import com.cstasenko.mixclouddiscover.di.ApplicationComponentProvider
import com.cstasenko.mixclouddiscover.di.ApplicationModule

class MixcloudDiscoverApplication : Application(), ApplicationComponentProvider {

    companion object {
        @Volatile private var APPLICATION_COMPONENT_INSTANCE: ApplicationComponent? = null

        fun getApplicationComponent(app: Application): ApplicationComponent {
           return APPLICATION_COMPONENT_INSTANCE ?: synchronized(this) {
                APPLICATION_COMPONENT_INSTANCE ?: buildComponent(app).also { APPLICATION_COMPONENT_INSTANCE = it }
            }
        }

        private fun buildComponent(app: Application) =
            DaggerApplicationComponent.builder().applicationModule(
                ApplicationModule(app)
            )
                .build()

    }

    override fun provideApplicationComponent(): ApplicationComponent {
        return getApplicationComponent(this)
    }
}
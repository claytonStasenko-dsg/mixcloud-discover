package com.cstasenko.mixclouddiscover.di

import android.app.Application
import com.cstasenko.mixclouddiscover.BuildConfig
import com.cstasenko.mixclouddiscover.repository.MixcloudRepository
import com.cstasenko.mixclouddiscover.repository.MixcloudRepositoryImpl
import com.cstasenko.mixclouddiscover.service.MixcloudApiService
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            ).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mixcloud.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesMixcloudApiService(retrofit: Retrofit): MixcloudApiService {
        return retrofit.create(MixcloudApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesMixcloudRepository(mixcloudApiService: MixcloudApiService): MixcloudRepository {
        return MixcloudRepositoryImpl(mixcloudApiService)
    }
}

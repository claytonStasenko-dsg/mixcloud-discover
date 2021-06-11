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
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
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
            .baseUrl(MIXCLOUD_API_BASE_URL)
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

    companion object {
        const val TIMEOUT: Long = 60L
        const val MIXCLOUD_API_BASE_URL = "https://api.mixcloud.com"
    }
}

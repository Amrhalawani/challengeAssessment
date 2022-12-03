package com.amrh.data.matches.remote.di

import com.amrh.data.BuildConfig
import com.amrh.data.matches.remote.HeadersInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesOkhttpClient(
        logger: Interceptor,
        headersInterceptor: HeadersInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(logger)
            .addInterceptor(headersInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            )
    }


}

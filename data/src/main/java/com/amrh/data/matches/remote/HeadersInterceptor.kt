package com.amrh.data.matches.remote

import com.amrh.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

object HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().addHeader(
                AUTH_TOKEN_KEY, BuildConfig.API_SECRET_KEY
            ).build()
        )
    }
}

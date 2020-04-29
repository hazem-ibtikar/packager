package com.monh.packager.di.modules

import com.monh.packager.BuildConfig
import com.monh.packager.data.remote.auth.UserService
import com.monh.packager.data.remote.orders.OrdersService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(isDebug: Boolean): OkHttpClient {
        val logging = HttpLoggingInterceptor()

        if (isDebug)
            logging.level = HttpLoggingInterceptor.Level.BODY
        else logging.level = HttpLoggingInterceptor.Level.NONE

        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(logging)

        return okHttpClientBuilder.build()
    }

    @Provides
    fun provideOrdersApi(retrofit: Retrofit): OrdersService = retrofit.create(OrdersService::class.java)

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

}
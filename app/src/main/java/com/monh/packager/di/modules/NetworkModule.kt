package com.monh.packager.di.modules

import com.monh.packager.BuildConfig
import com.monh.packager.data.locale.SharedPreferencesUtils
import com.monh.packager.data.remote.auth.UserService
import com.monh.packager.data.remote.orders.OrdersService
import com.monh.packager.data.remote.products.ProductsService
import com.monh.packager.data.remote.seller.SellerService
import com.monh.packager.utils.ApiKeys
import com.monh.packager.utils.toBearerToken
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
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
    fun provideOkHttp(isDebug: Boolean, @Named("HeadersInterceptor") headerInterceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()

        if (isDebug)
            logging.level = HttpLoggingInterceptor.Level.BODY
        else logging.level = HttpLoggingInterceptor.Level.NONE

        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(MAX_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(MAX_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(MAX_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(headerInterceptor)

        return okHttpClientBuilder.build()
    }


    @Singleton
    @Provides
    @Named("HeadersInterceptor")
    internal fun provideHeadersInterceptor(sharedPreferencesUtils: SharedPreferencesUtils): Interceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder().apply {
            sharedPreferencesUtils.userLoginResponse?.token?.let {
                addHeader(ApiKeys.AUTHORIZATION, it.toBearerToken())
            }
            addHeader(ApiKeys.X_APP_SECRET, "123456789")
            addHeader(ApiKeys.LANGUAGE, sharedPreferencesUtils.currentLanguage)
        }.build()
        chain.proceed(newRequest)
    }
    @Provides
    fun provideOrdersApi(retrofit: Retrofit): OrdersService = retrofit.create(OrdersService::class.java)

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Provides
    fun provideSellerService(retrofit: Retrofit): SellerService = retrofit.create(SellerService::class.java)

    @Provides
    fun provideProductsService(retrofit: Retrofit): ProductsService = retrofit.create(ProductsService::class.java)

}
const val MAX_TIME_OUT = 30L
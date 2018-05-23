package com.example.steven.stk.module

import android.content.Context
import com.example.steven.stk.API_END_POINT
import com.example.steven.stk.API_V2_END_POINT
import com.example.steven.stk.data.network.STKService
import com.example.steven.stk.data.network.STKService2
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesSTKService(@Named("STK") retrofit: Retrofit): STKService {
        return retrofit.create(STKService::class.java)
    }

    @Provides
    @Singleton
    fun providesSTKService2(@Named("STK_V2") retrofit: Retrofit): STKService2 {
        return retrofit.create(STKService2::class.java)
    }


    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun providesCache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000) //10MB Cache
    }

    @Provides
    @Singleton
    fun providesCacheFile(context: Context): File {
        return File(context.getCacheDir(), "okhttp_cache")
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Provides
    @Singleton
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Named("STK")
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .baseUrl(API_END_POINT)
                .build()
    }

    @Provides
    @Named("STK_V2")
    @Singleton
    fun providesRetrofit2(okHttpClient: OkHttpClient, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .baseUrl(API_V2_END_POINT)
                .build()
    }
}
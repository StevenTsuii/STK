package com.example.steven.stk.module

import android.content.Context
import com.example.steven.stk.API_END_POINT
import com.example.steven.stk.data.network.STKService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesSTKService(retrofit: Retrofit): STKService {
        return retrofit.create(STKService::class.java)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
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
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //asynchronous network request
                .addConverterFactory(GsonConverterFactory.create(gson))//etw output wrapper
                .client(okHttpClient)
                .baseUrl(API_END_POINT)
                .build()
    }
}
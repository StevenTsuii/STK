package com.example.steven.stk.data.network

import com.example.steven.stk.API_END_POINT
import com.example.steven.stk.API_V2_END_POINT
import com.example.steven.stk.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ServiceFactory{

     var stkService : STKService

    init {
        stkService = createSTKService()
    }

    fun createSTKService() : STKService{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //asynchronous network request
                .baseUrl(API_END_POINT)
                .client(getOkHttpClient())
                .build()
                .create(STKService::class.java)
    }

    fun createSTKService2() : STKService2{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //asynchronous network request
                .baseUrl(API_V2_END_POINT)
                .client(getOkHttpClient())
                .build()
                .create(STKService2::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .build()
    }
}
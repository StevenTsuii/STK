package com.example.steven.stk.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by steven on 23/4/2018.
 */
@Module
class AppModule(context: Context) {
    var context = context

    @Provides
    @Singleton
    fun providesContext() = context

    @Provides
    @Singleton
    fun providesCreatedTimestamp() = "Created at: ${System.currentTimeMillis()}"
}
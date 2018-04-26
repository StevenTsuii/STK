package com.example.steven.stk.module

import android.content.Context
import com.example.steven.stk.annotation.ActivityScope
import com.example.steven.stk.repo.AppRepository
import dagger.Module
import dagger.Provides

/**
 * Created by steven on 25/4/2018.
 */
@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun providesAppRepository(context: Context) = AppRepository(context)
}
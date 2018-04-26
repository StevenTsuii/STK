package com.example.steven.stk.module

import android.content.Context
import com.example.steven.stk.annotation.FragmentScope
import com.example.steven.stk.data.FakeData1
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by steven on 26/4/2018.
 */
@Module
class FragmentModule {

    @Provides
    @Named("FRAGMENT_MODULE_CREATED_TIME")
    @FragmentScope
    fun providesFragmentModuleCreatetedTime() = "FragmentModuleCreatetedTime: ${System.currentTimeMillis()}"

    @Provides
    @FragmentScope
    fun providesFakeData1(context : Context) = FakeData1(context)

}
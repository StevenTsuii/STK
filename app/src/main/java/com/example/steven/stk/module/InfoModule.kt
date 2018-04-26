package com.example.steven.stk.module

import com.example.steven.stk.data.DeviceInfo
import dagger.Module
import dagger.Provides

/**
 * Created by steven on 23/3/2018.
 */
@Module
class InfoModule {

    @Provides
    fun providesTagName(): String = "Initialized by InfoModule"

    @Provides
    fun providesDeviceInfo(tag: String): DeviceInfo = DeviceInfo(tag)


}
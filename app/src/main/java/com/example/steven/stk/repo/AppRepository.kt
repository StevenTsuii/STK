package com.example.steven.stk.repo

import android.content.Context
import com.example.steven.stk.BuildConfig

/**
 * Created by steven on 25/4/2018.
 */
class AppRepository(context : Context){
    var context = context

    fun getPackageName() = context.packageName

    fun getVersionName() = BuildConfig.VERSION_NAME

    fun getVersionCode() = BuildConfig.VERSION_CODE
}
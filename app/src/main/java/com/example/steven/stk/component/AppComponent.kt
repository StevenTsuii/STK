package com.example.steven.stk.component

import com.example.steven.stk.App
import com.example.steven.stk.module.ActivityModule
import com.example.steven.stk.module.AppModule
import com.example.steven.stk.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by steven on 18/4/2018.
 */
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
@Singleton
interface AppComponent {
    fun inject(app: App)
    fun plusActivityComponent(activityModule: ActivityModule): ActivityComponent
}
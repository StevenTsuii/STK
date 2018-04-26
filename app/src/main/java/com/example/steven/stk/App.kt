package com.example.steven.stk

import android.app.Application
import android.util.Log
import com.example.steven.stk.component.ActivityComponent
import com.example.steven.stk.component.AppComponent
import com.example.steven.stk.component.DaggerAppComponent
import com.example.steven.stk.module.ActivityModule
import com.example.steven.stk.module.AppModule
import javax.inject.Inject

/**
 * Created by steven on 23/4/2018.
 */
class App : Application() {

    companion object {
        lateinit var instance: App
            private set

    }

    lateinit var mAppComponent: AppComponent
    private lateinit var mActivityComponent: ActivityComponent

    @Inject
    lateinit var createdTimestamp: String

    override fun onCreate() {
        super.onCreate()
        instance = this
        mAppComponent = DaggerAppComponent.builder().appModule(AppModule(instance)).build()
        mAppComponent.inject(this)
        mActivityComponent = mAppComponent.plusActivityComponent(ActivityModule())
        Log.d("StevenCheck", createdTimestamp)
    }


    fun plusActivityComponent(): ActivityComponent {
        if (mActivityComponent == null) {
            mActivityComponent = mAppComponent.plusActivityComponent(ActivityModule())
        }
        return mActivityComponent
    }

}
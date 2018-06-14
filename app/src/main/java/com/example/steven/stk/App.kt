package com.example.steven.stk

import android.app.Application
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.example.steven.stk.component.ActivityComponent
import com.example.steven.stk.component.AppComponent
import com.example.steven.stk.component.DaggerAppComponent
import com.example.steven.stk.component.FragmentComponent
import com.example.steven.stk.module.ActivityModule
import com.example.steven.stk.module.AppModule
import com.example.steven.stk.module.FragmentModule
import com.google.android.gms.ads.MobileAds
import io.fabric.sdk.android.Fabric
import javax.inject.Inject



/**
 * Created by steven on 23/4/2018.
 */
class App : Application() {

    companion object {
        lateinit var instance: App
            private set

    }

    private lateinit var mAppComponent: AppComponent
    private var mActivityComponent: ActivityComponent? = null
    private var mFragmentComponent: FragmentComponent? = null

    @Inject
    lateinit var createdTimestamp: String

    override fun onCreate() {
        super.onCreate()
        instance = this
        MobileAds.initialize(this, ADMOB_APP_ID)
        Fabric.with(this, Crashlytics())
        mAppComponent = DaggerAppComponent.builder().appModule(AppModule(instance)).build()
        mAppComponent.inject(this)
        Log.d("StevenCheck", createdTimestamp)


    }


    fun plusActivityComponent(): ActivityComponent {
        if (mActivityComponent == null) {
            mActivityComponent = mAppComponent.plusActivityComponent(ActivityModule())
        }
        return mActivityComponent as ActivityComponent
    }

    fun plusFragmentComponent(): FragmentComponent {
        if (mFragmentComponent == null) {
            mFragmentComponent = plusActivityComponent().plusFragmentComponent(FragmentModule())
        }
        return mFragmentComponent as FragmentComponent
    }

    fun clearActivityComponent() {
        mActivityComponent.let { mActivityComponent = null }
    }

}
package com.example.steven.stk.module

import android.content.Context
import com.example.steven.stk.annotation.ActivityScope
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides

/**
 * Created by steven on 26/6/2018.
 */
@Module
class FirebaseModule {

    @Provides
    @ActivityScope
    fun providesFirebaseAnalytics(context: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }
}
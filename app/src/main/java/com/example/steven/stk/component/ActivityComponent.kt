package com.example.steven.stk.component

import com.example.steven.stk.activity.*
import com.example.steven.stk.annotation.ActivityScope
import com.example.steven.stk.module.*
import dagger.Subcomponent

/**
 * Created by steven on 25/4/2018.
 */

@Subcomponent(modules = arrayOf(ActivityModule::class, AdModule::class, PlayerModule::class, FirebaseModule::class))
@ActivityScope
interface ActivityComponent {
    fun plusFragmentComponent(fragmentModule: FragmentModule): FragmentComponent
    fun inject(mainActivity: MainActivity)
    fun inject(articleDetailActivity: ArticleDetailActivity)
    fun inject(testGTMActivity: TestGTMActivity)
    fun inject(myCloudActivity: MyCloudActivity)
    fun inject(snsActivity: SnsActivity)
}
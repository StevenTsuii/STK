package com.example.steven.stk.component

import com.example.steven.stk.activity.MainActivity
import com.example.steven.stk.annotation.ActivityScope
import com.example.steven.stk.module.ActivityModule
import com.example.steven.stk.module.AdModule
import com.example.steven.stk.module.FragmentModule
import com.example.steven.stk.module.PlayerModule
import dagger.Subcomponent

/**
 * Created by steven on 25/4/2018.
 */

@Subcomponent(modules = arrayOf(ActivityModule::class, AdModule::class, PlayerModule::class))
@ActivityScope
interface ActivityComponent {
    fun plusFragmentComponent(fragmentModule: FragmentModule): FragmentComponent
    fun inject(mainActivity: MainActivity)
}
package com.example.steven.stk.component

import com.example.steven.stk.MainActivity
import com.example.steven.stk.annotation.ActivityScope
import com.example.steven.stk.module.ActivityModule
import com.example.steven.stk.module.FragmentModule
import dagger.Subcomponent

/**
 * Created by steven on 25/4/2018.
 */

@Subcomponent(modules = arrayOf(ActivityModule::class))
@ActivityScope
interface ActivityComponent {
    fun plusFragmentComponent(fragmentModule: FragmentModule): FragmentComponent
    fun inject(mainActivity: MainActivity)
}
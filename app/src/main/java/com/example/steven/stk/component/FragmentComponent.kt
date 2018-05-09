package com.example.steven.stk.component

import com.example.steven.stk.fragment.MainFragment
import com.example.steven.stk.annotation.FragmentScope
import com.example.steven.stk.module.FragmentModule
import dagger.Subcomponent

/**
 * Created by steven on 26/4/2018.
 */
@Subcomponent(modules = arrayOf(FragmentModule::class))
@FragmentScope
interface FragmentComponent {
    fun inject(mainFragment: MainFragment)
}
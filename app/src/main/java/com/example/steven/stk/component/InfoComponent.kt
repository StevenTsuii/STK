package com.example.steven.stk.component

import com.example.steven.stk.MainFragment
import com.example.steven.stk.module.InfoModule
import com.example.steven.stk.module.UserModule
import dagger.Component

/**
 * Created by steven on 23/3/2018.
 */

@Component(modules = arrayOf(InfoModule::class, UserModule::class))
interface InfoComponent {
    //fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
}
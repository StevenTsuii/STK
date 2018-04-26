package com.example.steven.stk.component

import com.example.steven.stk.LifeSpan
import com.example.steven.stk.annotation.CustomScope
import dagger.Subcomponent

/**
 * Created by steven on 18/4/2018.
 */
@CustomScope
@Subcomponent
interface LifeSubComponent{
    fun inject(lifeSpan: LifeSpan)
}
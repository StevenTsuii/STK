package com.example.steven.stk.component

import dagger.Component
import javax.inject.Singleton

/**
 * Created by steven on 27/3/2018.
 */
@Singleton
@Component
interface LifeComponent {
   fun getLifeSubComponent() : LifeSubComponent
}
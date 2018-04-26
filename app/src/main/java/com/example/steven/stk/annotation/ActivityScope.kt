package com.example.steven.stk.annotation

import javax.inject.Scope

/**
 * Created by steven on 26/4/2018.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope(val value: String = "")
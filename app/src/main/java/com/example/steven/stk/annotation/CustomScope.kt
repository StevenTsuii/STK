package com.example.steven.stk.annotation

import javax.inject.Scope

/**
 * Created by steven on 27/3/2018.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CustomScope(val value: String = "")
package com.example.steven.stk.annotation

import javax.inject.Qualifier

/**
 * Created by steven on 26/3/2018.
 */
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Choose(val value : String = "")
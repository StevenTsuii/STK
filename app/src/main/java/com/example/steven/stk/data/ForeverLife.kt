package com.example.steven.stk.data

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by steven on 28/3/2018.
 */
@Singleton
class ForeverLife @Inject constructor(){
    var createdTimestamp = System.currentTimeMillis()
}
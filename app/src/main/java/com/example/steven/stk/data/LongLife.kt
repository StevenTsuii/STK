package com.example.steven.stk.data

import com.example.steven.stk.annotation.CustomScope
import javax.inject.Inject

/**
 * Created by steven on 27/3/2018.
 */

@CustomScope
class LongLife @Inject constructor(){
    var createdTimestamp = System.currentTimeMillis()
}
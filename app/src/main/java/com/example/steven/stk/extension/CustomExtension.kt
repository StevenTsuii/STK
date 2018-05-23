package com.example.steven.stk.extension

import android.app.Fragment
import android.content.Context
import android.util.Log

/**
 * Created by steven on 23/5/2018.
 */
fun Context.log(message : String) = Log.d("stLog", "${message}")

fun Fragment.log(message : String) = Log.d("stLog", "${message}")
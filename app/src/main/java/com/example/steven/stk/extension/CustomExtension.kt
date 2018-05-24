package com.example.steven.stk.extension

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import com.example.steven.stk.App
import com.example.steven.stk.component.ActivityComponent
import com.example.steven.stk.component.FragmentComponent

/**
 * Created by steven on 23/5/2018.
 */
fun Context.log(message: String) = Log.d("stLog", "${message}")

fun Fragment.log(message: String) = Log.d("stLog", "${message}")

fun Fragment.plugFragmentComponent(): FragmentComponent {
    return App.instance.plusFragmentComponent()
}

fun Activity.plugActivityComponent(): ActivityComponent {
    return App.instance.plusActivityComponent()
}

fun Context.toast(text: String) = {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(text: String) = {
    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
}
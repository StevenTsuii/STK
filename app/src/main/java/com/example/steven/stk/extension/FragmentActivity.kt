package com.example.steven.stk.extension

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.example.steven.stk.App
import com.example.steven.stk.component.ActivityComponent

fun FragmentActivity.addFragment(fragment : Fragment, containerId : Int){
    supportFragmentManager.inTransaction { add(containerId, fragment) }
}

fun FragmentActivity.replaceFragment(fragment : Fragment, containerId : Int){
    supportFragmentManager.inTransaction { replace(containerId, fragment) }
}

fun Activity.plugActivityComponent(): ActivityComponent {
    return App.instance.plusActivityComponent()
}

inline fun <reified T: Activity> Activity.startActivity() {
    startActivity( Intent(this, T::class.java))
}
package com.example.steven.stk.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
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

@SuppressLint("RestrictedApi")
fun BottomNavigationView.disableShiftMode() {
    val menuView = this.getChildAt(0) as BottomNavigationMenuView

    try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false

        for (i in 0 until menuView.childCount) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShiftingMode(false)
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    }
}
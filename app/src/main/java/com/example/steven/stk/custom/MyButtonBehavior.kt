package com.example.steven.stk.custom

import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.view.View
import android.widget.Button


/**
 * Created by steven on 21/6/2018.
 */
class MyButtonBehavior: CoordinatorLayout.Behavior<Button>(){

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: Button?, dependency: View?): Boolean {
        return dependency == AppBarLayout::class
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: Button?, dependency: View?): Boolean {


        val top = dependency?.y

        val layoutParams = child?.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.topMargin = top as Int
        child?.layoutParams = layoutParams

        return true
    }

    private fun setPosition(v: View, x: Int, y: Int) {
        val layoutParams = v.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.leftMargin = x
        layoutParams.topMargin = y
        v.layoutParams = layoutParams
    }
}
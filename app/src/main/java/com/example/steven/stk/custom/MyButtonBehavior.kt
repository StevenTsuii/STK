package com.example.steven.stk.custom

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button


/**
 * Created by steven on 21/6/2018.
 */
class MyButtonBehavior(context: Context, attributeSet: AttributeSet) :
        CoordinatorLayout.Behavior<Button>(context, attributeSet) {

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: Button?, dependency: View?): Boolean {
        Log.d("stevencheck", "layoutDependsOn: ${dependency != AppBarLayout::class}")
        return dependency is FlyMeButton
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: Button?, dependency: View?): Boolean {
        Log.d("stevencheck", "onDependentViewChanged: ${dependency?.y}")

//        val top = dependency?.y
//
//        val layoutParams = child?.layoutParams as CoordinatorLayout.LayoutParams
//        layoutParams.topMargin = top!!.toInt()
//        child?.layoutParams = layoutParams

        child?.animate()?.y(dependency!!.y.toFloat())
        return true
    }

    private fun setPosition(v: View, x: Int, y: Int) {
        val layoutParams = v.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.leftMargin = x
        layoutParams.topMargin = y
        v.layoutParams = layoutParams
    }
}
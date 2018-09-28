package com.example.steven.stk.extension

import android.support.v4.app.Fragment
import com.example.steven.stk.App
import com.example.steven.stk.component.FragmentComponent

fun Fragment.addFragment(fragment : Fragment, containerId : Int){
    activity?.addFragment(fragment, containerId)
}

fun Fragment.replaceFragment(fragment : Fragment, containerId : Int){
    activity?.replaceFragment(fragment, containerId)
}

fun Fragment.toast(text: String) = {
    activity?.toast(text)
}

fun Fragment.plugFragmentComponent(): FragmentComponent {
    return App.instance.plusFragmentComponent()
}
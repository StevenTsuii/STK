package com.example.steven.stk.gtm

import android.util.Log
import com.google.android.gms.tagmanager.Container

/**
 * Created by steven on 28/6/2018.
 */
class CustomTagCallback : Container.FunctionCallTagCallback {
    override fun execute(tagName: String, parameters: Map<String, Any>) {
        Log.i("CustomTagCallback", "Custom function call tag :$tagName is fired.")
    }
}

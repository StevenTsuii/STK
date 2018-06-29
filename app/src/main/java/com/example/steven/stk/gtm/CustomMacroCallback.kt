package com.example.steven.stk.gtm

import android.util.Log
import com.example.steven.stk.R.id.name
import com.google.android.gms.tagmanager.Container

/**
 * Created by steven on 28/6/2018.
 */
class CustomMacroCallback : Container.FunctionCallMacroCallback{
    override fun getValue(p0: String?, p1: MutableMap<String, Any>?): Any {
        Log.d("TagManager", "CustomMacroCallback:" + name)
        return name
    }

}
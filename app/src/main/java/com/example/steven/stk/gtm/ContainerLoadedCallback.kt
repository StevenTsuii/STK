package com.example.steven.stk.gtm

import android.util.Log
import com.google.android.gms.tagmanager.Container
import com.google.android.gms.tagmanager.ContainerHolder
import com.google.android.tagmanager.examples.cuteanimals.ContainerHolderSingleton.containerHolder

/**
 * Created by steven on 28/6/2018.
 */
class ContainerLoadedCallback: ContainerHolder.ContainerAvailableListener{
    override fun onContainerAvailable(p0: ContainerHolder?, p1: String?) {
        val container = containerHolder?.container
        registerCallbacksForContainer(container)
    }

    companion object {
        fun registerCallbacksForContainer(container: Container?){
//            // Register two custom function call macros to the container.
//            container?.registerFunctionCallMacroCallback("increment", CustomMacroCallback())
//            container?.registerFunctionCallMacroCallback("mod", CustomMacroCallback())
//            // Register a custom function call tag to the container.
//            container?.registerFunctionCallTagCallback("custom_tag", CustomTagCallback())


            container?.registerFunctionCallTagCallback("clickMyButton", ClickMyButtonFunctionTagCallback())
            container?.registerFunctionCallTagCallback("readArticle", ReadArticleFunctionTagCallback())
        }
    }
}

class ClickMyButtonFunctionTagCallback : Container.FunctionCallTagCallback{
    override fun execute(p0: String?, p1: MutableMap<String, Any>?) {
        Log.d("ClickMyButton", "ClickMyButton p0:${p0} p1 size:${p1?.size}")
        Log.d("ClickMyButton", "ClickMyButton get firstItem:${p1?.get("ButtonName")}")
    }
}

class ReadArticleFunctionTagCallback : Container.FunctionCallTagCallback{
    override fun execute(p0: String?, p1: MutableMap<String, Any>?) {
        Log.d("readArticle", "readArticle p0:${p0} p1 size:${p1?.size}")
        Log.d("readArticle", "readArticle articleName: ${p1?.get("articleName")}")
    }
}


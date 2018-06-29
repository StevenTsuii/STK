package com.example.steven.stk.gtm

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
            // Register two custom function call macros to the container.
            container?.registerFunctionCallMacroCallback("increment", CustomMacroCallback())
            container?.registerFunctionCallMacroCallback("mod", CustomMacroCallback())
            // Register a custom function call tag to the container.
            container?.registerFunctionCallTagCallback("custom_tag", CustomTagCallback())
        }
    }
}


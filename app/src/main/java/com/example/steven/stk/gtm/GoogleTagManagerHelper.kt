package com.example.steven.stk.gtm

import android.content.Context
import android.util.Log
import com.google.android.gms.tagmanager.DataLayer
import com.google.android.gms.tagmanager.TagManager

/**
 * Created by steven on 28/6/2018.
 */
class GoogleTagManagerHelper {
    companion object {
        fun pushPageViewEvent(context: Context, screenName: String) {
            pushEvent(context, "pageViewEvent", "pageViewName", screenName)
        }

        fun pushAnyActionCanTriggerEvent(context:Context, value: String){
//            ContainerHolderSingleton.containerHolder?.refresh()
            val dataLayer = TagManager.getInstance(context).dataLayer
           // dataLayer.push(DataLayer.mapOf("event", "anyActionCanTrigger", "Action", "anyActionCanTrigger"))
            dataLayer.push(DataLayer.mapOf("event", "pageViewEvent", "pageViewName", "pageViewEvent"))

           // dataLayer.push(DataLayer.mapOf("event", "anyActionCanTrigger", "Action", "anyActionCanTrigger"))//, "Category", "CategoryName", "Label", "LabelName", "Value", "value123"))
//            dataLayer.push(DataLayer.mapOf("event", "anyActionCanTrigger", "Action", "anyActionCanTrigger", "Category", "CategoryName", "Label", "LabelName", "Value", "value123"))
//            dataLayer.pushEvent("anyActionCanTrigger", DataLayer.mapOf("Action", "anyActionCanTrigger", "Category", "CategoryName", "Label", "LabelName", "Value", "value123"))
//            dataLayer.push(DataLayer.mapOf("event", "anyActionCanTrigger", "Action", "anyActionCanTrigger", "Category", "Category", "Label", "Label", "Value", "Value"))
//            dataLayer.push(DataLayer.mapOf("event", "anyActionCanTrigger", "Action", "anyActionCanTrigger", "Category", "anyActionCanTrigger", "Label", "anyActionCanTrigger", "Value", "anyActionCanTrigger"))

//            pushEvent(context, "pushEvent", "anyActionCanTrigger", "Action", v)
        }

        fun pushEvent(context: Context, eventValue: String, key: String, value: String) {
            val dataLayer = TagManager.getInstance(context).dataLayer
            dataLayer.push(DataLayer.mapOf("event", eventValue, key, value))

            Log.d("TagManager", "pushPageViewEvent=>$key:$value")
        }
    }
}
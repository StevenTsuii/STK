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

        fun pushBottomBarButtonTag(context: Context) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "clickBottomBarButton"))
        }

        fun pushBottomBarButtonTag2(context: Context) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "clickBottomBarButton", "AAA", "123", "Name", "hahaha"))
        }

        fun pushBottomBarButtonTag3(context: Context) {
            TagManager.getInstance(context).dataLayer.pushEvent("clickBottomBarButton", DataLayer.mapOf("AAA", "123", "Name", "hahaha"))
        }

        fun pushScreenViewTag(context: Context) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "screenViewEvent"))
        }

        fun pushScreenViewTag2(context: Context) {
            TagManager.getInstance(context).dataLayer.pushEvent("screenViewEvent", DataLayer.mapOf("ScreenName", "ScreenTag2"))
        }

        fun pushScreenViewTag3(context: Context) {
            TagManager.getInstance(context).dataLayer.pushEvent("screenViewEvent", DataLayer.mapOf("ScreenName", "ScreenTag3"))
        }



        fun pushNavigationButtonTag(context: Context, buttonName: String) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "clickNavigationButtonEvent", "navigationButtonName", buttonName))
//            TagManager.getInstance(context).dataLayer.pushEvent("clickNavigationButtonEvent", DataLayer.mapOf(
////                    "Category", "NavigationButton",
////                    "Action", "Click",
////                    "Label", "bottom button",
//                    "navigationButtonName", buttonName))
        }


        fun pushReadArticleTag(context: Context, articleName: String) {
            TagManager.getInstance(context).dataLayer.pushEvent("readArticleEvent", DataLayer.mapOf("articleName", articleName))
        }

        fun pushReadArticleTag2(context: Context, articleName: String) {
            TagManager.getInstance(context).dataLayer.pushEvent("readArticleEvent", DataLayer.mapOf("articleName", articleName))
        }

        fun pushReadArticleTag3(context: Context, articleName: String) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "readArticleEvent", "articleName", articleName))
        }

        fun pushNormalEventTag(context: Context, category: String, action: String, label: String, value:String){
            TagManager.getInstance(context).dataLayer.pushEvent("normalEvent", DataLayer.mapOf("NormalLabel", "haha label22", "NormalValue", "haha value11"))
//            TagManager.getInstance(context).dataLayer.pushEvent("normalEvent", DataLayer.mapOf("NormalValue", "haha value11"))
//            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "normalEvent", "NormalLabel", label, "NormalValue", value))
        }

        fun pushEvent(context: Context, eventValue: String, key: String, value: String) {
            val dataLayer = TagManager.getInstance(context).dataLayer
            dataLayer.push(DataLayer.mapOf("event", eventValue, key, value))

            Log.d("TagManager", "pushPageViewEvent=>$key:$value")
        }
    }
}
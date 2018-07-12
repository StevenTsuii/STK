package com.example.steven.stk.gtm

import android.content.Context
import android.util.Log
import com.google.android.gms.tagmanager.DataLayer
import com.google.android.gms.tagmanager.TagManager
import com.google.android.tagmanager.examples.cuteanimals.ContainerHolderSingleton

/**
 * Created by steven on 28/6/2018.
 */
class GoogleTagManagerHelper {
    companion object {
        fun pushPageViewEvent(context: Context, screenName: String) {
            pushEvent(context, "pageViewEvent", "pageViewName", screenName)
        }

        fun pushBottomBarButtonTag(context: Context) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "clickBottomBarButton", "Action", "GoodAction", "Category", "GoodCategory", "Label", "GoodLabel", "Value", 20))
        }

        fun pushBottomBarButtonTag2(context: Context) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "clickBottomBarButton", "Action", "StevenAction", "Category", "StevenCategory", "Label", "StevenLabel", "Value", "40"))
        }

        fun pushBottomBarButtonTag3(context: Context) {
            TagManager.getInstance(context).dataLayer.pushEvent("clickBottomBarButton", DataLayer.mapOf("NormalLabel", "NormalLabel111", "NormalValue", "NormalLabel222"))
        }

        fun pushScreenViewTag(context: Context) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "screenViewEvent", "cm_weather", "sunny", "cm_traffic", "good"))
        }

        fun pushScreenViewTag2(context: Context) {
            TagManager.getInstance(context).dataLayer.pushEvent("screenViewEvent", DataLayer.mapOf("ScreenName", "RainingScreen", "cm_weather", "raining", "cm_traffic", "bad"))
        }

        fun pushScreenViewTag3(context: Context) {
            TagManager.getInstance(context).dataLayer.pushEvent("screenViewEvent", DataLayer.mapOf("ScreenName", "CloudyScreen", "cm_weather", "cloudy"))
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
            TagManager.getInstance(context).dataLayer.pushEvent("readArticleEvent", DataLayer.mapOf("articleName", articleName, "cm_articleId", "article_000", "cm_active", "true", "ScreenName", null, "articlePosition", "0"))
        }

        fun pushReadArticleTag2(context: Context, articleName: String) {
            TagManager.getInstance(context).dataLayer.pushEvent("readArticleEvent", DataLayer.mapOf( "ScreenName", "ArticleDetailScreen02", "articleName", articleName, "cm_userType", "Premium","cm_articleId", "article_001", "cm_brand", "NewBrand", "cm_date", "2018-07-11", "articlePosition", "1"))
        }

        fun pushReadArticleTag3(context: Context, articleName: String) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "readArticleEvent", "articleName", articleName,  "cm_articleId", "article_002", "cm_userType", "Guest", "ScreenName", "ArticleDetailScreen03"))
        }

        fun pushNormalEventTag(context: Context, category: String, action: String, label: String, value:String){
            TagManager.getInstance(context).dataLayer.pushEvent("normalEvent", DataLayer.mapOf("NormalLabel", "haha label22", "NormalValue", "haha value11"))
//            TagManager.getInstance(context).dataLayer.pushEvent("normalEvent", DataLayer.mapOf("NormalValue", "haha value11"))
//            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "normalEvent", "NormalLabel", label, "NormalValue", value))
        }


        fun pushMultiDataOne(context: Context) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "multiDataEvent", "DataLabel", "ddlabel"))
        }

        fun pushMultiDataTwo(context: Context) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "multiDataEvent", "DataLabel", "ddlabel2", "ScreenName", "MultiDataScreen"))
        }


        fun pushFreeUser(context: Context) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "freeUserEvent"))
        }

        fun pushPaidUser(context: Context) {
            TagManager.getInstance(context).dataLayer.push(DataLayer.mapOf("event", "paidUserEvent"))
        }

        fun refreshContainer(){
            ContainerHolderSingleton.containerHolder?.refresh()
        }

        fun pushEvent(context: Context, eventValue: String, key: String, value: String) {
            val dataLayer = TagManager.getInstance(context).dataLayer
            dataLayer.push(DataLayer.mapOf("event", eventValue, key, value))

            Log.d("TagManager", "pushPageViewEvent=>$key:$value")
        }
    }
}
package com.example.steven.stk.data.model


/**
 * Created by steven on 17/5/2018.
 */
object SideMenuModel {
    data class BaseResponse(val resTime: Int, val state: String, val content: ArrayList<SideMenuItem>)



    data class SideMenuItem(
            val menuId: String,
            val displayName: String,
            val landingDisplay: String,
            val pixelChannel: String,
            val pixelSection: String,
            val pixelSubSection: String,
            val pixelSubSubSection: String,
            val pixelNews: String,
            val pixelCat: String,
            val api: String,
            val leftDisplay: String,
            val topDisplay: String,
            val pixelContent: String,
            val apiv2: String,
            val submenu: Any,
            val theme: Any,
            val topMenu: Any,
            val topButton: Any,
            val action: String,
            val leftMenuLayout: String,
            val layout: String,
            val sortingBar: ArrayList<Any>,
            val displayImage: String,
            val pixelCategory: String,
            val pixelNewsType: String,
            val categoryId: String
    )


}
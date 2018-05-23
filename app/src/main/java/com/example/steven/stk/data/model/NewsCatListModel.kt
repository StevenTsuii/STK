package com.example.steven.stk.data.model

/**
 * Created by steven on 23/5/2018.
 */
object NewsCatListModel{
    data class BaseResponse(val resTime: Int, val state: String, val content: ArrayList<NewsCatItem>)

    data class NewsCatItem(
            val _id: String,
            val id: String,
            val api: String,
            val action: String,
            val order: Int,
            val isCompulsory: Boolean,
            val isFixedPosition: Boolean,
            val isVisible: Boolean,
            val name: String,
            val layout: String,
            val href: String,
            val type: String,
            val menuId: String
    )
}
package com.example.steven.stk.data.model

data class StartUpModel(val state: String, val content: ContentModel) {


    data class ContentModel(val lastUpdateDate: LastUpdateDate) {


        data class LastUpdateDate(val startup: String, val sideMenu: String, val adTag: String)
    }
}
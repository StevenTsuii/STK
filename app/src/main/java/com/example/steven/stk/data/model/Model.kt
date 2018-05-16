package com.example.steven.stk.data.model

import java.lang.reflect.Type

/**
 * Created by steven on 15/5/2018.
 */
object Model {

    // data class BaseResponse()

    data class StartUp(val resTime: Int, val state: String, val content: StartUpContent)

    data class StartUpContent(val lastUpdateDate: LastUpdateDate, val landingLogo: LandingLogo, val serverPath: ServerPath)

    data class LastUpdateDate(val startup: String, val sideMenu: String, val adTag: String)

    data class LandingLogo(val path: String, val updateDate: String)

    data class ServerPath(val apiUrl: String, val articleDetailPublicAPIPath: String, val articleDetailPremiumAPIPath: String, val newsCatListAPIPath: String)

}
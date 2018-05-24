package com.example.steven.stk.data.network

import com.example.steven.stk.data.model.SideMenuModel
import com.example.steven.stk.data.model.StartUpModel
import io.reactivex.Observable
import retrofit2.http.GET

interface STKService {

    @GET("startup")
    fun startUp(): Observable<StartUpModel.BaseResponse>

    @GET("SideMenu")
    fun sideMenu(): Observable<SideMenuModel.BaseResponse>

}
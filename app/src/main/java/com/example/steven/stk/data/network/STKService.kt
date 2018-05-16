package com.example.steven.stk.data.network

import com.example.steven.stk.data.model.Model
import com.example.steven.stk.data.model.ResponseModel
import io.reactivex.Observable
import retrofit2.http.GET

interface STKService {

    @GET("startup")
    fun startUp(): Observable<Model.StartUp>

    @GET("SideMenu")
    fun sideMenu(): Observable<ResponseModel>
}
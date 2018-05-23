package com.example.steven.stk.data.network

import com.example.steven.stk.data.model.NewsCatListModel
import io.reactivex.Observable
import retrofit2.http.GET

interface STKService2 {

    @GET("newsCatlist")
    fun newsCatList(): Observable<NewsCatListModel.BaseResponse>
}
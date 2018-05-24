package com.example.steven.stk.data.network

import com.example.steven.stk.data.model.ArticleListModel
import com.example.steven.stk.data.model.NewsCatListModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface STKService2 {

    @GET("ANDROID/v2/1/newsCatlist")
    fun newsCatList(): Observable<NewsCatListModel.BaseResponse>

    @GET("v1/1/ArticleList")
    fun articleList(
            @Query("Type") type: String,
            @Query("Start") start: String,
            @Query("Offset") offset: String
    ): Observable<ArticleListModel.BaseResponse>
}
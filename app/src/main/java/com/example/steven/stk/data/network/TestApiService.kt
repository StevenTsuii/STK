package com.example.steven.stk.data.network

import com.example.steven.stk.data.model.ResponseModel
import io.reactivex.Observable
import retrofit2.http.GET

interface TestApiService {

    @GET("app/version.aspx")
    fun version(): Observable<ResponseModel>
}
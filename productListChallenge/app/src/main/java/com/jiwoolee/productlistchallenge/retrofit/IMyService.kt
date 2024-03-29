package com.jiwoolee.productlistchallenge.retrofit

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface IMyService {
    @GET("products")
    fun pagingList(@Query("skin_type") skin_type: String, @Query("page") page: Int): Observable<String>

    @GET("products")
    fun queryProduct(@Query("skin_type") skin_type: String, @Query("search") search: String): Observable<String>
}

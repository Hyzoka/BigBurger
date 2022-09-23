package com.test.bigburger.repo

import com.test.bigburger.model.Menu
import com.test.bigburger.utils.CONTENT_TYPE_JSON
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface MenuApi {

    @GET("bigburger")
    @Headers(CONTENT_TYPE_JSON)
    fun getMenus(): Observable<Response<List<Menu>>>
}
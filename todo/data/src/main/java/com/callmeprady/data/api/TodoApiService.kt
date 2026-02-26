package com.callmeprady.data.api

import com.callmeprady.core.net.Network
import com.callmeprady.domain.TodoModel
import retrofit2.Response
import retrofit2.create
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos() : Response<List<TodoModel>>

    companion object{
        val apiService = Network.retrofit.create<TodoApiService>()
    }
}
package com.callmeprady.data.api


import com.callmeprady.domain.TodoModel
import retrofit2.Response
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos() : Response<List<TodoModel>>

}
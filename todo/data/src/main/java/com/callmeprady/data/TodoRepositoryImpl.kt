package com.callmeprady.data

import android.util.Log
import com.callmeprady.core.net.Network
import com.callmeprady.data.api.TodoApiService
import com.callmeprady.domain.TodoModel
import com.callmeprady.domain.TodoRepository
import com.callmeprady.domain.TodoResponse
import kotlinx.coroutines.delay
import retrofit2.create

class TodoRepositoryImpl constructor( private val todoApiService : TodoApiService) : TodoRepository {


    override suspend fun fetchTodos(): TodoResponse {
        val response = todoApiService.getTodos()
        Log.d("TD_R", response.body().toString())
        if(response.isSuccessful){
            response.body()?.let {
                return TodoResponse.Success(it)
            }
                .run {
                    return TodoResponse.Error("null response")
                }
        }
        else{
            return TodoResponse.Error("network error : ${response.message()}")
        }

    }

    override suspend fun addTodo(todo: TodoModel) {

    }
}


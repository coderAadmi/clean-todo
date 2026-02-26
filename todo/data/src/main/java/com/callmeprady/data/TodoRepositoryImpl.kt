package com.callmeprady.data

import com.callmeprady.data.api.TodoApiService
import com.callmeprady.domain.TodoModel
import com.callmeprady.domain.TodoRepository
import com.callmeprady.domain.TodoResponse

class TodoRepositoryImpl constructor(private val todoApiService: TodoApiService) : TodoRepository {

    override suspend fun fetchTodos(): TodoResponse {
        val response = todoApiService.getTodos()
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


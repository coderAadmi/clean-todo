package com.callmeprady.domain

interface TodoRepository{
     suspend fun fetchTodos () : TodoResponse
     suspend fun addTodo(todo : TodoModel)
 }
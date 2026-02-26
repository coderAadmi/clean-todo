package com.callmeprady.domain

interface TodoRepository{
     fun fetchTodos () : TodoResponse
     fun addTodo(todo : TodoModel)
 }
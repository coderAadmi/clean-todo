package com.callmeprady.domain

sealed class TodoResponse {
     object Loading : TodoResponse()
     data class Error(val msg : String) : TodoResponse()
     data class Success(val todos : List<TodoModel>) : TodoResponse()
 }
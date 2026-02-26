package com.callmeprady.domain

class AddTodoUsecase constructor(private val repository: TodoRepository){
    suspend operator fun invoke(todoModel: TodoModel) = repository.addTodo(todoModel)
 }
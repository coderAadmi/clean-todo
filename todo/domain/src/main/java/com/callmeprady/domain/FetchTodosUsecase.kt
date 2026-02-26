package com.callmeprady.domain

class FetchTodosUsecase constructor(private val repository: TodoRepository) {
    suspend operator fun invoke() = repository.fetchTodos()
}
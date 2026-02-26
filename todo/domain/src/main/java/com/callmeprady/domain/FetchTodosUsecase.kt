package com.callmeprady.domain

import javax.inject.Inject

class FetchTodosUsecase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke() = repository.fetchTodos()
}
package com.callmeprady.ui.screens

import com.callmeprady.domain.TodoModel

sealed class TodoScreenState {
    object Nothing : TodoScreenState()
    data object  Loading : TodoScreenState()
    data class Error(val msg : String) : TodoScreenState()
    data class Success(val todos : List<TodoUiModel>) : TodoScreenState()
}

data class TodoUiModel(
    val userId : Int,
    val id : Int,
    val title : String,
)

fun TodoModel.toUiModel() = TodoUiModel(userId, id, title)
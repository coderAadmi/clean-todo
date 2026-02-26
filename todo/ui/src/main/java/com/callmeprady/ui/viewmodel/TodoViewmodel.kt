package com.callmeprady.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.callmeprady.domain.FetchTodosUsecase
import com.callmeprady.domain.TodoResponse
import com.callmeprady.ui.screens.TodoScreenState
import com.callmeprady.ui.screens.toUiModel
import kotlinx.coroutines.launch

class TodoViewmodel constructor(private val fetchTodoUsecase : FetchTodosUsecase) : ViewModel() {

    private val _todoScreenState = MutableLiveData<TodoScreenState>(TodoScreenState.Nothing) //only mutasble within viewmodel
    val todoScreenState : LiveData<TodoScreenState> = _todoScreenState // immutable livedata exposed to ui

    fun loadTodos(){
        viewModelScope.launch {
           val response =  fetchTodoUsecase()
            when(response){
                is TodoResponse.Error -> {
                    _todoScreenState.value = TodoScreenState.Error(response.msg)
                }
                TodoResponse.Loading -> {
                    _todoScreenState.value = TodoScreenState.Loading
                }
                is TodoResponse.Success -> {
                    _todoScreenState.value = TodoScreenState.Success(
                        response.todos.map {
                            it.toUiModel()
                        }
                    )
                }
            }
        }
    }
}
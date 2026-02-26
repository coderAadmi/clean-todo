package com.callmeprady.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.callmeprady.domain.FetchTodosUsecase
import com.callmeprady.domain.TodoResponse
import com.callmeprady.ui.screens.TodoScreenState
import com.callmeprady.ui.screens.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoViewmodel constructor(private val fetchTodoUsecase : FetchTodosUsecase) : ViewModel() {

    private val _todoScreenState = MutableLiveData<TodoScreenState>(TodoScreenState.Loading) //only mutasble within viewmodel
    val todoScreenState  = _todoScreenState
        .asFlow()
        .stateIn(viewModelScope, SharingStarted.Lazily, TodoScreenState.Loading)
    // immutable livedata exposed to ui

    fun loadTodos(){
        viewModelScope.launch {
           val response =  fetchTodoUsecase()
            Log.d("TD_VM", response.toString())
            when(response){
                is TodoResponse.Error -> {
                    withContext(Dispatchers.Main) {
                        _todoScreenState.value = TodoScreenState.Error(response.msg)
                    }
                }
                TodoResponse.Loading -> {
                    withContext(Dispatchers.Main) {
                        _todoScreenState.value = TodoScreenState.Loading
                    }
                }
                is TodoResponse.Success -> {
                    withContext(Dispatchers.Main) {
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
}
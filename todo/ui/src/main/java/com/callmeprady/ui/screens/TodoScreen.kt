package com.callmeprady.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.callmeprady.ui.viewmodel.TodoViewmodel

@Composable
fun TodoScreen(
    modifier: Modifier = Modifier,
    todovm: TodoViewmodel
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    var screenState : TodoScreenState = TodoScreenState.Nothing

    LaunchedEffect(Unit) {
        todovm.loadTodos()
        todovm.todoScreenState
            .observe(lifecycleOwner){
                screenState = it
            }
    }


    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        when(screenState){
            is TodoScreenState.Error -> {
                Text("Error fetching todos : ${(screenState as TodoScreenState.Error).msg}")
            }
            TodoScreenState.Loading -> {
                Text("Loading todos.....")
            }
            TodoScreenState.Nothing -> {

            }
            is TodoScreenState.Success -> {
                val todos = (screenState as TodoScreenState.Success).todos
                LazyColumn(modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)) {

                    items(todos){
                        Card(Modifier.fillMaxSize()) {
                            Column(Modifier.fillMaxSize().padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text("title : ${it.title}")
                                Text("id : ${it.id}")
                                Text("userid :${it.userId}")
                            }
                        }
                    }

                }
            }
        }

    }

}
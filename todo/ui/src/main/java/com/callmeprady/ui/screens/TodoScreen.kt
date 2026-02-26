package com.callmeprady.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.callmeprady.ui.viewmodel.TodoViewmodel

@Composable
fun TodoScreen(
    modifier: Modifier = Modifier,
    todovm: TodoViewmodel
) {

    LaunchedEffect(Unit) {
        todovm.loadTodos()
    }

}
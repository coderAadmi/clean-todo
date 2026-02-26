package com.callmeprady.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.callmeprady.data.TodoRepositoryImpl
import com.callmeprady.data.api.TodoApiService
import com.callmeprady.domain.FetchTodosUsecase
import com.callmeprady.todo.ui.theme.TodoTheme
import com.callmeprady.ui.screens.TodoScreen
import com.callmeprady.ui.viewmodel.TodoViewmodel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val todoVm by viewModels<TodoViewmodel>(factoryProducer = {
                object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return TodoViewmodel(fetchTodoUsecase = FetchTodosUsecase(TodoRepositoryImpl(
                            TodoApiService.apiService
                        ))) as T
                    }
                }
            })

            TodoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    TodoScreen(
                        modifier = Modifier.padding(innerPadding),
                        todovm = todoVm
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoTheme {
        Greeting("Android")
    }
}
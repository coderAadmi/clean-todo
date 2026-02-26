package com.callmeprady.di

import com.callmeprady.data.TodoRepositoryImpl
import com.callmeprady.data.api.TodoApiService
import com.callmeprady.domain.FetchTodosUsecase
import com.callmeprady.domain.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Provides
    @Singleton
    fun provideTodoApi(retrofit : Retrofit) : TodoApiService{
        return retrofit.create<TodoApiService>()
    }

    @Provides
    @Singleton
    fun provideFetchUsecase(todoRepository: TodoRepository) : FetchTodosUsecase{
        return FetchTodosUsecase(todoRepository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class TodoRepoModule{
    @Binds
    @Singleton
    abstract fun bindTR(impl: TodoRepositoryImpl) : TodoRepository
}
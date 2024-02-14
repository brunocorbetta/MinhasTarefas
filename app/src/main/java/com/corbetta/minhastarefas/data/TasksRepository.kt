package com.corbetta.minhastarefas.data

import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun insertItem(task: Task)

    suspend fun deleteItem(task: Task)

    suspend fun updateItem(task: Task)

    fun getAllItemsStream(): Flow<List<Task>>

}
package com.corbetta.minhastarefas.data

interface TasksRepository {

    suspend fun insertItem(task: Task)

    suspend fun deleteItem(task: Task)

    suspend fun updateItem(task: Task)

}
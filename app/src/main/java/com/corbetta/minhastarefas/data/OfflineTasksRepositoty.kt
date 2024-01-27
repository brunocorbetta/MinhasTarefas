package com.corbetta.minhastarefas.data

class OfflineTasksRepositoty(private val taskDao: TaskDao) : TasksRepository {

    override suspend fun insertItem(task: Task) = taskDao.insert(task)

    override suspend fun deleteItem(task: Task) = taskDao.delete(task)

    override suspend fun updateItem(task: Task) = taskDao.update(task)

}
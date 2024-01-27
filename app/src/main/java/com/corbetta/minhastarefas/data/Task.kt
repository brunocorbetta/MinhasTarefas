package com.corbetta.minhastarefas.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tarefas")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val descricao: String
)
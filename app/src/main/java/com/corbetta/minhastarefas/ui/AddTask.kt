package com.corbetta.minhastarefas.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.corbetta.minhastarefas.AppViewModelProvider
import com.corbetta.minhastarefas.data.Task
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTask(navigateBack: () -> Unit,
            viewModel: AddTaskViewModel = viewModel(factory = AppViewModelProvider.Factory
            )){


    val coroutineScope = rememberCoroutineScope()


    Column (modifier = Modifier
        .fillMaxSize()
        .padding(28.dp)
    ) {

        TaskBody(
            taskUiState = viewModel.taskUiState ,
            onTaskValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveTask()
                    navigateBack()
                }
            }
        )
    }
}

@Composable
fun TaskBody(
    taskUiState: TaskUiState,
    onTaskValueChange: (TaskDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(28.dp)
    ) {

        TaskInputForm(
            taskDetails = taskUiState.taskDetails,
            onValueChange = onTaskValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = onSaveClick,
            enabled = taskUiState.isEntryValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 6.dp)
        ) {
            Text(text = "Cadastrar")
        }
    }
}

@Composable
fun TaskInputForm(
    taskDetails: TaskDetails,
    modifier: Modifier = Modifier,
    onValueChange: (TaskDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(28.dp)
    ) {
        Text(
            text = "Cadastre Suas Tarefas",
            fontSize = 26.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            textAlign = TextAlign.Center,

            )
        OutlinedTextField(value = taskDetails.titulo,
            onValueChange = {onValueChange(taskDetails.copy(titulo = it))},
            label = { Text(text = "titulo")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(value = taskDetails.descricao,
            onValueChange = {onValueChange(taskDetails.copy(descricao = it))},
            label = { Text(text = "descrição")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            enabled = enabled,
            singleLine = true
        )

    }
}
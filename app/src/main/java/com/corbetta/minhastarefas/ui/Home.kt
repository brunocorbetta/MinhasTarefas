package com.corbetta.minhastarefas.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.corbetta.minhastarefas.AppViewModelProvider
import com.corbetta.minhastarefas.R
import com.corbetta.minhastarefas.data.Task


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
         ) {

    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(18.dp),
                onClick = {
                          navController.navigate("AddTask")
                },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        }
    ) {
        Column(Modifier.fillMaxSize()) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Tarefas",
                    fontSize = 26.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    textAlign = TextAlign.Center,

                )
            }
           HomeBody(taskList = homeUiState.taskList,
               onItemClick = {}
           )
        }
    }
}

@Composable
private fun HomeBody(
    taskList: List<Task>, onItemClick: (Int) -> Unit, modifier: Modifier = Modifier
        ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier) {
            if (taskList.isEmpty()) {
                Text(text = "Nada para mostrar!")
            } else {
                TaskList(taskList = taskList, onItemClick = { onItemClick(it.id) })
            }
    }
}

@Composable
fun TaskList(
    taskList: List<Task>, onItemClick: (Task) -> Unit, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = taskList, key = { it.id }) {item ->
            TaskItem(task = item,
                modifier = Modifier
                .clickable { onItemClick(item) } )
        }
    }
}

@Composable
private fun TaskItem(
    task: Task, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(

        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = task.titulo)
                Spacer(Modifier.weight(1f))
                Text(text = task.descricao)
            }
        }
    }
}


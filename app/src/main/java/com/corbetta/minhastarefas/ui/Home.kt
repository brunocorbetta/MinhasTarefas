package com.corbetta.minhastarefas.ui


import android.annotation.SuppressLint
import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.corbetta.minhastarefas.AppViewModelProvider
import com.corbetta.minhastarefas.data.Task
import kotlinx.coroutines.launch



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
         ) {

    val coroutineScope = rememberCoroutineScope()
    val homeUiState by viewModel.homeUiState.collectAsState()
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    var selectedItemId by rememberSaveable { mutableStateOf(0) }
    var taskToDelete by rememberSaveable { mutableStateOf<Task?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(1.dp),
                onClick = {
                          navController.navigate("AddTask")
                },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        },
    ) {

        Surface (
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .width(500.dp)
                .height(80.dp)



        ) {
            Text(
                text = "Tarefas",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),



                )
        }



        Column(modifier = Modifier
            .fillMaxSize()
            .padding(1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {




            if (deleteConfirmationRequired) {
                DeleteTask(
                    onDeleteConfirm = {
                        deleteConfirmationRequired = false
                        coroutineScope.launch {
                            taskToDelete?.let { viewModel.deleteItem(it) }
                        }
                    },
                    onDeleteCancel = { deleteConfirmationRequired = false },
                )
            }

            HomeBody(taskList = homeUiState.taskList,
                onItemClick = { itemId ->
                    taskToDelete = homeUiState.taskList.find { it.id == itemId }
                    selectedItemId = itemId
                    deleteConfirmationRequired = true

                },
                modifier = Modifier
                    .padding(1.dp)


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
                TaskList(taskList = taskList, onItemClick = onItemClick, modifier = modifier)

            }

    }
}

@Composable
fun TaskList(
    taskList: List<Task>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(items = taskList, key = { it.id }) {item ->
            TaskItem(task = item,
                onItemClick = onItemClick)

        }
    }
}

@Composable
private fun TaskItem(
    task: Task, modifier: Modifier = Modifier,  onItemClick: (Int) -> Unit
) {
    ElevatedCard(

        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(8.dp),


        modifier = modifier
            .width(250.dp)
            .height(200.dp)
            .padding(6.dp)
            .clickable { onItemClick(task.id) }


    ) {
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.padding(6.dp)

        ) {
                Text(
                    text = task.titulo,
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = modifier.padding(4.dp)

                )
                Text(
                    text = task.descricao,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = modifier.padding(4.dp)

                )
        }
    }
}


@Composable
private fun DeleteTask(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { /**/ },
        title = { Text(text = "Atenção") },
        text = { Text(text = "Deletar Tarefa?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "não")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Sim")
            }
        }
    )

}

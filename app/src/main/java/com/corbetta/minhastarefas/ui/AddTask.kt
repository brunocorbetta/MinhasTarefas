package com.corbetta.minhastarefas.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTask(){

    var title by rememberSaveable { mutableStateOf("") }
    var descrition by rememberSaveable { mutableStateOf("") }

    Column (modifier = Modifier
        .fillMaxSize()
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
        TextField(value = title,
            onValueChange = {title = it},
            label = { Text(text = "titulo")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(value = descrition,
            onValueChange = {descrition = it},
            label = { Text(text = "descrição")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(start = 4.dp, top = 6.dp)
        ) {
            Text(text = "Cadastrar")
        }
    }
}
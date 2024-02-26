package br.com.alexf.dailytask.ui.features.taskForm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alexf.dailytask.ui.theme.DailyTaskTheme

@Composable
fun TaskFormScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = "minha primeira tarefa",
            onValueChange = {},
            Modifier
                .fillMaxWidth()
        )
        TextField(
            value = "criar uma tarefa a partir do App Android",
            onValueChange = {},
            Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Button(
            onClick = { /*TODO*/ },
            Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Salvar"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskFormScreenPreview() {
    DailyTaskTheme {
        TaskFormScreen()
    }
}
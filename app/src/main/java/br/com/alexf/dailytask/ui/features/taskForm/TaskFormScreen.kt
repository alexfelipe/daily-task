package br.com.alexf.dailytask.ui.features.taskForm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alexf.dailytask.models.Task
import br.com.alexf.dailytask.ui.theme.DailyTaskTheme

@Composable
fun TaskFormScreen(
    onSaveClick: (Task) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var title by remember {
            mutableStateOf("")
        }
        var description by remember {
            mutableStateOf("")
        }
        TextField(
            value = title,
            onValueChange = {
                title = it
            },
            Modifier
                .fillMaxWidth()
        )
        TextField(
            value = description,
            onValueChange = {
                description = it
            },
            Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Button(
            onClick = {
                onSaveClick(
                    Task(
                        title = title,
                        description = description
                    )
                )
            },
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
        TaskFormScreen(
            onSaveClick = {}
        )
    }
}
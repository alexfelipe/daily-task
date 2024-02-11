package br.com.alexf.dailytask.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alexf.dailytask.models.Task
import br.com.alexf.dailytask.ui.theme.DailyTaskTheme

@Composable
fun TaskFormScreen(
    onSaveClick: (Task) -> Unit,
    modifier: Modifier = Modifier,
    isSaving: Boolean = false
) {
    var isSavingState by remember {
        mutableStateOf(isSaving)
    }
    Column(
        modifier
            .fillMaxSize()
            .padding(8.dp)
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
                .fillMaxWidth(),
            label = {
                Text(text = "Título")
            }
        )
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp),
            label = {
                Text(text = "Descrição")
            }
        )


        when (isSavingState) {
            true -> {
                Box(Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        Modifier
                            .padding(vertical = 8.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            else -> {
                Button(
                    onClick = {
                        isSavingState = true
                        onSaveClick(
                            Task(
                                title = title,
                                description = description
                            )
                        )
                    }, Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Salvar")
                }
            }
        }
    }
}

@Preview
@Composable
private fun TaskFormScreenPreview() {
    DailyTaskTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TaskFormScreen(
                onSaveClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun TaskFormScreenWithIsSavingStateAsTruePreview() {
    DailyTaskTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TaskFormScreen(
                onSaveClick = {},
                isSaving = true
            )
        }
    }
}
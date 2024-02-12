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
import br.com.alexf.dailytask.mocks.generateLoremAt
import br.com.alexf.dailytask.models.Task
import br.com.alexf.dailytask.ui.theme.DailyTaskTheme

data class TaskFormState(
    val title: String = "",
    val onTitleChange: (String) -> Unit = {},
    val description: String = "",
    val onDescriptionChange: (String) -> Unit = {},
)

@Composable
fun TaskFormScreen(
    state: TaskFormState,
    onSaveClick: (Task) -> Unit,
    modifier: Modifier = Modifier,
    isSaving: Boolean = false
) {
    var isSavingState by remember {
        mutableStateOf(isSaving)
    }
    val title = state.title
    val description = state.description
    Column(
        modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        TextField(
            value = title,
            onValueChange = state.onTitleChange,
            Modifier
                .fillMaxWidth(),
            label = {
                Text(text = "Título")
            }
        )
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = state.onDescriptionChange,
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
                state = TaskFormState(),
                onSaveClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun TaskFormScreenWithContentInTextFieldsPreview() {
    DailyTaskTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TaskFormScreen(
                state = TaskFormState(
                    title = generateLoremAt(10),
                    description = generateLoremAt(20)
                ),
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
                state = TaskFormState(),
                onSaveClick = {},
                isSaving = true
            )
        }
    }
}
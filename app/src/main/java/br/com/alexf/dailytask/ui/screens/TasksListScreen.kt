package br.com.alexf.dailytask.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alexf.dailytask.mocks.generateTasks
import br.com.alexf.dailytask.models.Task
import br.com.alexf.dailytask.ui.theme.DailyTaskTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TasksListScreen(
    tasks: List<Task>,
    onNewTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize()) {
        when (tasks.isEmpty()) {
            true -> {
                Column(
                    Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.Done, null,
                        Modifier.size(64.dp),
                        tint = Color.Gray.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = "Sem tarefas",
                        Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = LocalTextStyle.current.copy(
                            fontSize = 24.sp,
                            color = Color.Gray.copy(alpha = 0.8f)
                        )
                    )
                }
            }

            false -> {
                LazyColumn(
                    Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(tasks) { task ->
                        var isCompact by rememberSaveable {
                            mutableStateOf(true)
                        }
                        TaskItem(
                            task, Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .clickable {
                                    isCompact = !isCompact
                                }
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray.copy(alpha = 0.5f),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(16.dp),
                            isCompact = isCompact
                        )
                    }
                }
            }
        }
        ExtendedFloatingActionButton(
            onClick = onNewTaskClick,
            Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                Icons.Default.Edit,
                contentDescription = "ícone de lápis para criar uma nova tarefa"
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "Nova tarefa")
        }
    }
}

@Composable
fun TaskItem(
    task: Task, modifier: Modifier = Modifier,
    isCompact: Boolean
) {
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = task.title, style = LocalTextStyle.current.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            ),
            maxLines = if (isCompact) {
                2
            } else {
                Int.MAX_VALUE
            },
            overflow = TextOverflow.Ellipsis
        )
        AnimatedVisibility(visible = !isCompact) {
            Text(text = task.description)
        }
        Text(
            text = task.createdAt.formatToBrazilianDate(), Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
}

private fun LocalDateTime.formatToBrazilianDate(): String {
    return DateTimeFormatter
        .ofPattern("dd/MM/yyyy HH:mm")
        .format(this)
}

@Preview
@Composable
private fun TaskItemPreview() {
    DailyTaskTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TaskItem(
                task = generateTasks().random(),
                isCompact = false
            )
        }
    }
}

@Preview
@Composable
private fun TaskItemWithCompactStatePreview() {
    DailyTaskTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TaskItem(
                task = generateTasks().random(),
                isCompact = true
            )
        }
    }
}

@Preview
@Composable
private fun TasksListWithoutTasksScreenPreview() {
    DailyTaskTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TasksListScreen(
                tasks = emptyList(),
                onNewTaskClick = {}
            )
        }
    }
}


@Preview
@Composable
private fun TasksListScreenPreview() {
    DailyTaskTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TasksListScreen(
                tasks = generateTasks(10),
                onNewTaskClick = {}
            )
        }
    }
}




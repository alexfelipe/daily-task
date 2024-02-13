package br.com.alexf.dailytask.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.alexf.dailytask.dao.TaskDao
import br.com.alexf.dailytask.dao.TaskDataState
import br.com.alexf.dailytask.ui.screens.TaskFormScreen
import br.com.alexf.dailytask.ui.screens.TaskFormState
import br.com.alexf.dailytask.ui.screens.TasksListScreen
import kotlinx.coroutines.flow.collectLatest
import java.util.UUID

@Composable
fun DailyTaskNavHost(navController: NavHostController) {
    val dao = remember {
        TaskDao()
    }
    NavHost(
        navController = navController,
        startDestination = "tasksList"
    ) {
        composable("tasksList") {
            val tasks by dao.tasks.collectAsState()
            TasksListScreen(
                tasks = tasks,
                onDeleteTask = {
                    dao.delete(it)
                },
                onEditTask = {
                    navController.navigate("taskForm?taskId=${it.id}")
                },
                onNewTaskClick = {
                    navController.navigate("taskForm")
                }
            )
        }
        composable("taskForm?taskId={id}") { navBackStateEntry ->
            val taskId = navBackStateEntry.arguments?.getString("id")
            var taskDataState by remember {
                mutableStateOf<TaskDataState>(TaskDataState.Loading)
            }
            LaunchedEffect(null) {
                dao.findTaskById(taskId)
                    .collectLatest {
                        taskDataState = it
                    }
            }
            var state by remember {
                mutableStateOf(TaskFormState())
            }

            when (taskDataState) {

                TaskDataState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                }

                is TaskDataState.Completed -> {
                    val task = (taskDataState as TaskDataState.Completed)
                        .task
                    val title = task?.title ?: ""
                    val description = task?.description ?: ""
                    LaunchedEffect(null) {
                        state = state.copy(
                            title = title,
                            description = description,
                            onTitleChange = {
                                state = state.copy(title = it)
                            },
                            onDescriptionChange = {
                                state = state.copy(description = it)
                            }
                        )
                    }
                    TaskFormScreen(
                        state = state,
                        onSaveClick = {
                            dao.save(
                                it.copy(
                                    id = taskId ?: UUID.randomUUID().toString()
                                )
                            )
                            navController.navigateUp()
                        })
                }
            }

        }
    }
}

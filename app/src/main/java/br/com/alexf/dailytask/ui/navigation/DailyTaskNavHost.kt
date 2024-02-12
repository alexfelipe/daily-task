package br.com.alexf.dailytask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.alexf.dailytask.dao.TaskDao
import br.com.alexf.dailytask.ui.screens.TaskFormScreen
import br.com.alexf.dailytask.ui.screens.TaskFormState
import br.com.alexf.dailytask.ui.screens.TasksListScreen

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
                onNewTaskClick = {
                    navController.navigate("taskForm")
                }
            )
        }
        composable("taskForm") {
            var state by remember {
                mutableStateOf(TaskFormState())
            }
            LaunchedEffect(null) {
                state = state.copy(
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
                onSaveClick = { task ->
                dao.save(task)
                navController.navigateUp()
            })
        }
    }
}

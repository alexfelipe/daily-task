package br.com.alexf.dailytask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.alexf.dailytask.dao.TaskDao
import br.com.alexf.dailytask.ui.screens.TaskFormScreen
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
            TaskFormScreen(onSaveClick = { task ->
                dao.save(task)
                navController.navigateUp()
            })
        }
    }
}

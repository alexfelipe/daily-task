package br.com.alexf.dailytask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.alexf.dailytask.models.Task
import br.com.alexf.dailytask.ui.features.taskForm.TaskFormScreen
import br.com.alexf.dailytask.ui.features.tasksList.TasksListScreen
import br.com.alexf.dailytask.ui.theme.DailyTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val tasks = remember {
                        mutableStateListOf<Task>()
                    }
                    NavHost(navController = navController, startDestination = "tasksList") {
                        composable("tasksList") {
                            TasksListScreen(tasks.toList(), onNewTaskClick = {
                                navController.navigate("taskForm")
                            })
                        }
                        composable("taskForm") {
                            TaskFormScreen(
                                onSaveClick = { task ->
                                    tasks.add(task)
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
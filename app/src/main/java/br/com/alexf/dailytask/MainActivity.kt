package br.com.alexf.dailytask

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import br.com.alexf.dailytask.ui.features.taskForm.TaskFormScreen
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
                    TaskFormScreen(
                        onSaveClick = { task ->
                            Log.i("MainActivity", "onCreate: $task")
                        }
                    )

                }
            }
        }
    }
}
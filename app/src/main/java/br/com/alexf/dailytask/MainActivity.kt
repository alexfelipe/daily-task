package br.com.alexf.dailytask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import br.com.alexf.dailytask.ui.theme.DailyTaskTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TasksListScreen()
                }
            }
        }
    }
}

@Composable
fun TasksListScreen() {
    Column(
        Modifier.fillMaxSize()
    ) {
        TaskItem()
    }
}

@Composable
private fun TaskItem() {
    Column(
        Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = LoremIpsum(Random.nextInt(1, 10)).values.first(),
        )
        Text(
            text = LoremIpsum(Random.nextInt(1, 10)).values.first(),
            Modifier
                .border(
                    width = 1.dp,
                    color = Color.Gray.copy(alpha = 0.8f),
                    RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        )
        Text(
            text = "01/01/2024 00:00",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskItemPreview() {
    TaskItem()
}

@Preview(showBackground = true)
@Composable
private fun TasksListScreenPreview() {
    TasksListScreen()
}

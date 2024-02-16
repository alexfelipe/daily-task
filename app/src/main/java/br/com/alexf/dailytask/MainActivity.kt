package br.com.alexf.dailytask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.Blue)
                    ) {
                        Row {
                            Spacer(
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(Color.Red)
                            )
                            Column {
                                Spacer(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .background(Color.Gray)
                                )
                                Spacer(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .background(Color.Black)
                                )
                                Row {
                                    Spacer(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(Color.Cyan)
                                    )
                                    Spacer(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(Color.Magenta)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
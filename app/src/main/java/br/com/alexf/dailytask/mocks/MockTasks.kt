package br.com.alexf.dailytask.mocks

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.alexf.dailytask.models.Task
import java.time.LocalDateTime
import kotlin.random.Random

fun generateTasks(amount: Int = 2) = List(amount) {
    Task(
        title = LoremIpsum(Random.nextInt(1, 20)).values.first(),
        description = LoremIpsum(Random.nextInt(1, 20)).values.first(),
        createdAt = LocalDateTime.now()
    )
}
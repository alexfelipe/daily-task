package br.com.alexf.dailytask.mocks

import br.com.alexf.dailytask.models.Task
import java.time.LocalDateTime

fun generateTasks(amount: Int) = List(amount) {
    Task(
        title = "task ${it + 1}",
        description = "description ${it + 1}",
        createdAt = LocalDateTime.now()
    )
}
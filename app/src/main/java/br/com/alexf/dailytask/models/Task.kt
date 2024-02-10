package br.com.alexf.dailytask.models

import java.time.LocalDateTime

class Task(
    val title: String,
    val description: String,
    val isDone: Boolean = false,
    val createdAt: LocalDateTime
)
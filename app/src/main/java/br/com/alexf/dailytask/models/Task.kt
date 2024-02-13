package br.com.alexf.dailytask.models

import java.time.LocalDateTime
import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
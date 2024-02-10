package br.com.alexf.dailytask.dao

import br.com.alexf.dailytask.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskDao {

    val tasks = _tasks.asStateFlow()

    fun save(task: Task) {
        _tasks.update {
            it + task
        }
    }

    companion object {
        private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    }


}
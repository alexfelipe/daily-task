package br.com.alexf.dailytask.dao

import android.util.Log
import br.com.alexf.dailytask.mocks.generateTasks
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

    fun delete(task: Task) {
        _tasks.update { tasks ->
            (tasks - task).also {
                Log.i("TaskDao", "delete: $it")
            }
        }
    }

    companion object {
        private val _tasks = MutableStateFlow(
            generateTasks(10)
        )
    }


}
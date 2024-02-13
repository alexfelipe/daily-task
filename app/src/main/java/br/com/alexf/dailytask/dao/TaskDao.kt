package br.com.alexf.dailytask.dao

import br.com.alexf.dailytask.mocks.generateTasks
import br.com.alexf.dailytask.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

sealed class TaskDataState {

    data object Loading : TaskDataState()
    data class Completed(val task: Task?) : TaskDataState()

}

class TaskDao {

    val tasks = _tasks.asStateFlow()

    fun save(task: Task) {
        _tasks.update { tasks ->
            tasks.map {
                if (task.id == it.id) {
                    task
                } else {
                    it
                }
            }
        }
    }

    fun delete(task: Task) {
        _tasks.update { tasks ->
            (tasks - task)
        }
    }

    fun findTaskById(taskId: String?): Flow<TaskDataState> = flow {
        emit(TaskDataState.Loading)

        val foundTask = _tasks.value.firstOrNull {
            it.id == taskId
        }

        emit(TaskDataState.Completed(foundTask))
    }

    companion object {
        private val _tasks = MutableStateFlow(
            generateTasks(10)
        )
    }


}
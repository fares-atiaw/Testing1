package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class TasksViewModelTest {

    // Use a fake repository to be injected into the view-model
    private lateinit var tasksRepository: FakeTestRepository

    // Subject under test
    private lateinit var tasksViewModel: TasksViewModel

    // Define some code that run before and after each test runs
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()     // Executes each task synchronously using Architecture Components.

    @Before
    fun setup() {
        // We initialise the tasks to 3, with one active and two completed
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        tasksViewModel = TasksViewModel(tasksRepository)

    // tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {

        // When adding a new task and new task event is triggered
        tasksViewModel.addNewTask()
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()

        // Then
        assertThat(value.getContentIfNotHandled(), (not(nullValue())))
    }


    @Test
    fun setFilterAllTasks_tasksAddViewVisible(){
        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        assertThat(true, `is` (tasksViewModel.tasksAddViewVisible.getOrAwaitValue()))
        // assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
    }


}

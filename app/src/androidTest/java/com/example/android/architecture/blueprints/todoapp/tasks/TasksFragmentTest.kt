package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.ServiceLocator
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeAndroidTestRepository
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailFragment
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailFragmentArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class TasksFragmentTest
{
    private lateinit var repository: TasksRepository

    @Before
    fun initRepository() {
        repository = FakeAndroidTestRepository()
        ServiceLocator.tasksRepository = repository
    }


//    @Test
//    fun activeTaskDetails_Displayed() = runBlockingTest{
//        // GIVEN - Add active (incomplete) task to the DB
//        val activeTask = Task("Active Task", "AndroidX Rocks", false)
//        repository.saveTask(activeTask)
//
//        // WHEN - Details fragment launched to display task
//        val bundle = TasksFragmentArgs(activeTask.id).toBundle()
//        launchFragmentInContainer<TasksFragment>(bundle, R.style.AppTheme)
//        withContext(Dispatchers.IO) {
//            Thread.sleep(2000)
//        }
//
//    }



    @After
    fun cleanupDb() = runBlockingTest {
        ServiceLocator.resetRepository()
    }

}
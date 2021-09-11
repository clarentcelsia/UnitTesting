package com.example.unittesting.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.unittesting.CoroutineRule
import com.example.unittesting.getOrAwaitValueTest
import com.example.unittesting.repository.FakeRepositoryTest
import com.example.unittesting.util.Response
import com.example.unittesting.util.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ViewModelTest {

    /**
     * A JUnit Test Rule that swaps the background executor used by the Architecture Components
     * with a different one which executes each task synchronously.
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    /* In this test case I use coroutine that bounds to main dispatcher.
       Because Main dispatcher relies on Main Looper which is only available
       on real app environment.

       This test directory doesn't run on device but JVM which don't have access
       to that main dispatcher opposite to androidTest directory.
       So, I need to define JUnit rule to solve the coroutine.
     */
    @get:Rule
    var coroutineRule = CoroutineRule()

    private lateinit var myModel: TestViewModel

    @Before
    fun init(){
        myModel = TestViewModel(FakeRepositoryTest())
    }

    @Test
    fun `if the name is empty response error`(){
        myModel.validation("", 0, 0.0)
        val value = myModel.status.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

}
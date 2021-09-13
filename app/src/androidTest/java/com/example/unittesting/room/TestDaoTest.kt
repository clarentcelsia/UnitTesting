package com.example.unittesting.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.unittesting.getOrAwaitValue
import com.example.unittesting.resource.TestModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class TestDaoTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    /** This rule swaps the background task which works asynchronously become synchronously */
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    /*
    Inject database for testAppModule.
    Name to identify which module (TestModule / TestAppModule),
        we want to inject their db into our testDao test class.

        note: Remove keyword 'private', because we cannot inject into private variables
     */
    @Inject
    @Named("testDB")
    lateinit var database: TestDB

    private lateinit var dao: TestDao

    @Before
    fun setup(){
        // This actually make hilt inject all of our dependencies that
        // annotated with @Inject.
        hiltRule.inject()
        dao = database.testDao()
    }

    @After
    fun close(){
        database.close()
    }

    @Test
    fun insertData() = runBlockingTest{
        val item = TestModel("comb", 10, 20.0, 1)
        dao.insertData(item)

        //check the existence of the inserted item
        //make sure that the item is contained in db
        val getData = dao.getData().getOrAwaitValue()

        assertThat(getData).contains(item)
    }

    @Test
    fun deleteData() = runBlockingTest{
        val item = TestModel("comb", 10, 20.0, 1)
        dao.insertData(item)
        dao.deleteData(item)

        val getData = dao.getData().getOrAwaitValue()
        assertThat(getData).doesNotContain(item)
    }
}
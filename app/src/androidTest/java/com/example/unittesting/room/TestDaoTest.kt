package com.example.unittesting.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.unittesting.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TestDaoTest {

    // This rule swaps the background task which works asynchronously
    // become synchronously
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TestDB
    private lateinit var dao: TestDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TestDB::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = database.testDao()
    }

    @After
    fun close(){
        database.close()
    }

    @Test
    fun insertData() = runBlockingTest{
        val item = TestModel(1, "comb", 10, 20.0)
        dao.insertData(item)

        //check the existence of the inserted item
        //make sure that the item is contained in db
        val getData = dao.getData().getOrAwaitValue()

        assertThat(getData).contains(item)
    }

    @Test
    fun deleteData() = runBlockingTest{
        val item = TestModel(1, "comb", 10, 20.0)
        dao.insertData(item)
        dao.deleteData(item)

        val getData = dao.getData().getOrAwaitValue()
        assertThat(getData).doesNotContain(item)
    }
}
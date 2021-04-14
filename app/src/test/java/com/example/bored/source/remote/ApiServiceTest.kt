package com.example.bored.source.remote

import com.example.bored.utils.InjectorUtils
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ApiServiceTest {

    private lateinit var api : ApiService

    @Before
    fun setup() {
        api = InjectorUtils.providesApiService()
    }

    @Test
    fun getRandomActivity_onSuccess_returnsAnActivity() = runBlocking {
        val response = api.getRandomActivity()

        assertEquals(200, response.code())
        assertNotNull(response.body())
    }
}
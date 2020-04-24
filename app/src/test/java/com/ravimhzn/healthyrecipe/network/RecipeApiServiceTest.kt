package com.ravimhzn.healthyrecipe.network

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RecipeApiServiceTest {

    @Inject
    lateinit var apiService: RecipeApiService

    private var mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApiService::class.java)
    }

    @Test
    fun searchRecipeTest() {
        Assertions.assertNotNull(apiService)
    }

    @Test
    fun `When succeed with valid data, Then response is parsed`() {
//        val testData = TestData("hello!")
//        val testDataJson = "{\"name\":\"${testData.name}\"}"
//        val successResponse = MockResponse().setBody(testDataJson)
//        mockWebServer.enqueue(successResponse)
//
//        val response = testApi.test().execute()
//
//        mockWebServer.takeRequest()
//        assertThat(response.body()!!, is(testData))
    }

}
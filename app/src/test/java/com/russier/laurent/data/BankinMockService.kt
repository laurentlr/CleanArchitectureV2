package com.russier.laurent.data

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import java.io.IOException

class BankinMockService<T>(private val c: Class<T>) {

    private lateinit var mockServer: MockWebServer
    private lateinit var bankinRetrofit: BankinRetrofit

    private fun getJsonFromFile(file: String): String {
        println("MockWebServer load file : $file")
        val inputStreamResponse = this.javaClass.classLoader!!.getResourceAsStream(file)
        val size: Int
        return try {
            size = inputStreamResponse.available()
            val buffer = ByteArray(size)
            inputStreamResponse.read(buffer)
            inputStreamResponse.close()
            String(buffer)
        } catch (e: IOException) {
            Assert.assertTrue(false)
            ""
        }
    }

    fun enqueueResponse(code: Int, file: String) {
        val mockResponse = MockResponse()
        mockResponse.setBody(getJsonFromFile(file))
        mockServer.enqueue(mockResponse.setResponseCode(code))
    }

    fun init() {
        mockServer = MockWebServer()
        bankinRetrofit = BankinRetrofit(mockServer.url("/").toString())
    }

    fun get(): T {
        return bankinRetrofit.retrofit.create(c)
    }

    fun destroy() {
        mockServer.close()
    }
}


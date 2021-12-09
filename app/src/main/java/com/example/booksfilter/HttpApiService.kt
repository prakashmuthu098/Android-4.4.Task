package com.example.booksfilter
import retrofit2.http.GET
interface HttpApiService {
    @GET("books")
    suspend fun getBooks(): List<Books>
}
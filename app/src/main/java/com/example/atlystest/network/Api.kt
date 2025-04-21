package com.example.atlystest.network

import com.example.atlystest.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String
    ): List<Movie>
}
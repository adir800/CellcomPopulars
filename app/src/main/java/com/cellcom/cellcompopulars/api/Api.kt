package com.cellcom.cellcompopulars.api

import com.cellcom.cellcompopulars.populars.PopularsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular?api_key=2c46288716a18fb7aadcc2a801f3fc6b&language=en-US&page=1")
     fun getPopularMovies(): Call<PopularsResult>

    @GET("movie/popular?api_key=2c46288716a18fb7aadcc2a801f3fc6b&language=en-US")
    fun getPopularMoviesByPage(@Query("page") page: Int): Call<PopularsResult>

    @GET("movie/now_playing?api_key=2c46288716a18fb7aadcc2a801f3fc6b&language=en-US&page=1")
    fun getInTheaterMovies(): Call<PopularsResult>

    @GET("movie/now_playing?api_key=2c46288716a18fb7aadcc2a801f3fc6b&language=en-US")
    fun getInTheaterMoviesByPage(@Query("page") page: Int): Call<PopularsResult>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}
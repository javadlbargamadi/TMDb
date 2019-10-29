package com.example.themoviedb

import com.example.themoviedb.MovieListClass.MovieListClass
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBInterface {

    @GET("movie?api_key=7ac72bd9edccc294270b0fb0981b7fb9&language=en-US&page=1&include_adult=true")
    fun getMovieList(@Query("query") movieName: String): Observable<MovieListClass>
}

//https://api.themoviedb.org/3/movie/
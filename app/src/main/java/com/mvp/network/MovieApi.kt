package com.mvp.network

import com.mvp.interaction.MovieInteraction
import com.mvp.model.Movie
import com.mvp.model.responses.movieResponse.MovieResponse
import com.mvp.ui.MovieMapper
import com.mvp.utils.HttpConstants
import com.mvp.utils.PreferencesHelper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

class MovieApi(helper: PreferencesHelper) : NetworkProvider(helper), MovieInteraction {

    private val movieApiService: MovieApiService

    init {
        movieApiService = create(MovieApiService::class.java)
    }

    override fun getMovie(): Observable<Movie> {
        return movieApiService.login("d68be4d4feb143a49d3ee40896b85922").map(MovieMapper())
    }

    private interface MovieApiService {
        @Headers("Content-Type: application/json")
        @GET(HttpConstants.MOVIE_LIST)
        fun login(@Query("api_key") key:String): Observable<MovieResponse>
    }
}
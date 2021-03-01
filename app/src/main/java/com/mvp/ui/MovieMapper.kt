package com.mvp.ui

import com.mvp.model.Movie
import com.mvp.model.responses.movieResponse.MovieResponse
import com.mvp.utils.Utilities
import io.reactivex.functions.Function

class MovieMapper : Function<MovieResponse, Movie> {

    override fun apply(t: MovieResponse): Movie {
        val movie = Movie()
        if (Utilities.isNotEmpty(t.id)){
            movie.id = t.id.toString()
        }
        if (Utilities.isNotEmpty(t.backdropPath)){
            movie.backDrop = t.backdropPath
        }
       return movie
    }
}
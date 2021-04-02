package com.mvp.manager

import com.mvp.common.BaseManager
import com.mvp.common.NetworkHelper
import com.mvp.interaction.MovieInteraction
import com.mvp.model.Movie
import com.mvp.network.MovieApiProvider
import com.mvp.utils.PreferencesHelper
import io.reactivex.Observable

class MovieManager(helper: PreferencesHelper, networkHelper: NetworkHelper) :
    BaseManager(networkHelper), MovieInteraction {

    private val movieApi: MovieInteraction

    init {
        movieApi = MovieApiProvider(helper)
    }

    override fun getMovie(): Observable<Movie> {
        return movieApi.getMovie()
    }
}
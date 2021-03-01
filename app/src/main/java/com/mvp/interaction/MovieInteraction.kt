package com.mvp.interaction

import com.mvp.model.Movie
import io.reactivex.Observable

interface MovieInteraction {

    fun getMovie():Observable<Movie>
}
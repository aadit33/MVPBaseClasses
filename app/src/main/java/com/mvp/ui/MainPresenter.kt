package com.mvp.ui

import com.mvp.common.BaseObserver
import com.mvp.manager.MovieManager
import com.mvp.model.Movie

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter(view) {

    override fun onViewCreated() {
        val manager = MovieManager(view.preferences, view.networkHelper)
        executeAsync(manager.getMovie(), object : BaseObserver<Movie>() {
            override fun onOperationCompleted(data: Movie?) {
                println("data"+data?.id)
                println("data"+data?.backDrop)
            }

        })
    }

    override fun getScreenName(): String = "Main"
}
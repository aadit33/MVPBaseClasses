package com.mvp.ui

import android.os.Bundle
import com.mvp.R
import com.mvp.common.BaseActivity

class MainActivity : BaseActivity(), MainContract.View {

    private var presenter: MainContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        presenter?.onViewCreated()
    }
}
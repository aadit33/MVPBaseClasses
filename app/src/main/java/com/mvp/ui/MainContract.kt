package com.mvp.ui

import com.mvp.common.BasePresenter
import com.mvp.common.BaseView

interface MainContract {

    interface View: BaseView{

    }

    abstract class Presenter protected constructor(view: BaseView): BasePresenter(view){
        abstract fun onViewCreated()

    }
}
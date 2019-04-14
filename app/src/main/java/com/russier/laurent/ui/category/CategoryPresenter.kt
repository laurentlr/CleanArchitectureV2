package com.russier.laurent.ui.category

interface CategoryPresenter : CategoryClickListener {

    fun onViewReady()

    fun onDestroy()
}
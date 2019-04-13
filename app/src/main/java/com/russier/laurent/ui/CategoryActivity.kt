package com.russier.laurent.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.russier.laurent.R
import com.russier.laurent.domain.Category

class CategoryActivity : AppCompatActivity(), CategoriesView {

    lateinit var presenter: CategoriesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onViewReady()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun displayCategories(categories: List<Category>) {
        TODO()
    }

    override fun displayError() {
        Toast
            .makeText(this, "Error getting categories", Toast.LENGTH_LONG)
            .show()
    }

}

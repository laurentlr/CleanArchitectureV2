package com.russier.laurent.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.russier.laurent.R
import com.russier.laurent.domain.Category
import com.russier.laurent.injection.ComponentAccessor
import com.russier.laurent.injection.DaggerPresenterComponent
import com.russier.laurent.injection.PresenterModule
import javax.inject.Inject

class CategoryActivity : AppCompatActivity(), CategoriesView {

    @Inject
    lateinit var presenter: CategoriesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerPresenterComponent
            .builder()
            .domainComponent((applicationContext as ComponentAccessor).domainComponent())
            .presenterModule(PresenterModule(this))
            .build()
            .inject(this)

        presenter.onViewReady()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun displayCategories(categories: List<Category>) {
        Toast
            .makeText(this, "${categories.size}", Toast.LENGTH_SHORT)
            .show()
    }

    override fun displayError() {
        Toast
            .makeText(this, "Error getting categories", Toast.LENGTH_LONG)
            .show()
    }

}

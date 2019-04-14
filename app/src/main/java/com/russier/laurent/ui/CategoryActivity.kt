package com.russier.laurent.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.russier.laurent.R
import com.russier.laurent.domain.Category
import com.russier.laurent.injection.ComponentAccessor
import com.russier.laurent.injection.DaggerPresenterComponent
import com.russier.laurent.injection.PresenterModule
import javax.inject.Inject

class CategoryActivity : AppCompatActivity(), CategoriesView {

    @Inject
    lateinit var presenter: CategoriesPresenter
    private lateinit var recycler: RecyclerView
    private val adapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter

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
        adapter.setItems(categories)
    }

    override fun displayError() {
        Toast
            .makeText(this, "Error getting categories", Toast.LENGTH_LONG)
            .show()
    }

}

package com.russier.laurent.ui.category

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
import com.russier.laurent.ui.subcategory.SubCategoriesActivity
import javax.inject.Inject

class CategoryActivity : AppCompatActivity(), CategoryView {

    @Inject
    lateinit var presenter: CategoryPresenter
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerPresenterComponent
            .builder()
            .domainComponent((applicationContext as ComponentAccessor).domainComponent())
            .presenterModule(PresenterModule(this))
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CategoryAdapter(presenter)
        recycler = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter

        presenter.onViewReady()
    }

    override fun navigateToSubCategory(categoryId: String) {
        SubCategoriesActivity.launch(this, categoryId)
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

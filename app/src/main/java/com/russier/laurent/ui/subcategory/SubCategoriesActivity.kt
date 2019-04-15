package com.russier.laurent.ui.subcategory

import android.content.Context
import android.content.Intent
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
import com.russier.laurent.ui.category.CategoryAdapter
import javax.inject.Inject

class SubCategoriesActivity : AppCompatActivity(), SubCategoryView {

    companion object {
        private const val KEY_CATEGORY_ID = "KEY_CATEGORY_ID"
        fun launch(context: Context, categoryId: String) {
            context.startActivity(
                Intent(context, SubCategoriesActivity::class.java)
                    .apply { putExtra(KEY_CATEGORY_ID, categoryId) }
            )
        }
    }

    @Inject
    lateinit var presenter: SubCategoryPresenter
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: CategoryAdapter
    private lateinit var categoryId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerPresenterComponent
            .builder()
            .domainComponent((applicationContext as ComponentAccessor).domainComponent())
            .presenterModule(PresenterModule(this))
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        //saved instance should be handle
        categoryId = intent.extras.getString(KEY_CATEGORY_ID)
        setContentView(R.layout.subcategory_activity)

        adapter = CategoryAdapter()
        recycler = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter

        presenter.onViewReady(categoryId)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun displaySubCategories(subCategories: List<Category>) {
        adapter.setItems(subCategories)
    }

    override fun displayError() {
        Toast
            .makeText(this, "Error getting sub categories", Toast.LENGTH_LONG)
            .show()
    }
}
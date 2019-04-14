package com.russier.laurent.ui.subcategory

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class SubCategoriesActivity : AppCompatActivity() {

    companion object {
        private const val KEY_CATEGORY_ID = "KEY_CATEGORY_ID"
        fun launch(context: Context, categoryId: String) {
            context.startActivity(
                Intent(context, SubCategoriesActivity::class.java)
                    .apply { putExtra(KEY_CATEGORY_ID, categoryId) }
            )
        }
    }
}
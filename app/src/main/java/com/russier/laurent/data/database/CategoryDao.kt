package com.russier.laurent.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dbCategories: List<DbCategory>)

    @Query("SELECT * FROM category")
    fun selectAll(): Single<List<DbCategory>>

}

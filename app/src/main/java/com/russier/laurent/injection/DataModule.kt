package com.russier.laurent.injection

import com.russier.laurent.data.BankinRetrofit
import com.russier.laurent.data.CategoryRepositoryImpl
import com.russier.laurent.data.CategoryService
import com.russier.laurent.data.database.CategoryDao
import com.russier.laurent.domain.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [DataModule.Declaration::class])
class DataModule(
    private val categoryDao: CategoryDao
) {

    @Singleton
    @Provides
    internal fun provideRetrofit(): BankinRetrofit {
        return BankinRetrofit("https://raw.githubusercontent.com/bankin-engineering/challenge-android/master/")
    }

    @Singleton
    @Provides
    internal fun provideCategoryService(bankinRetrofit: BankinRetrofit): CategoryService {
        return bankinRetrofit.retrofit.create(CategoryService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideCategoryDao(): CategoryDao {
        return categoryDao
    }

    @Module
    interface Declaration {

        @Singleton
        @Binds
        fun bindCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository

    }
}
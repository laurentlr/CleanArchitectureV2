package com.russier.laurent.injection

import com.russier.laurent.data.BankinRetrofit
import com.russier.laurent.data.CategoryRepositoryImpl
import com.russier.laurent.data.CategoryService
import com.russier.laurent.domain.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [DataModule.Declaration::class])
class DataModule {

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

    @Module
    interface Declaration {

        @Singleton
        @Binds
        fun bindCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository

    }
}
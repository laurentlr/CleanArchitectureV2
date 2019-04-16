package com.russier.laurent.ui

import android.app.Application
import com.russier.laurent.data.database.DataBase
import com.russier.laurent.injection.*

class MyApplication : Application(), ComponentAccessor {

    private lateinit var domainComponent: DomainComponent

    override fun onCreate() {
        super.onCreate()

        val categoryDao = DataBase
            .getInstance(this)
            .categoryDao()

        val dataComponent = DaggerDataComponent
            .builder()
            .dataModule(DataModule(categoryDao))
            .build()

        domainComponent = DaggerDomainComponent
            .builder()
            .dataComponent(dataComponent)
            .build()
    }

    override fun domainComponent(): DomainComponent {
        return domainComponent
    }
}
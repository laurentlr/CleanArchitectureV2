package com.russier.laurent.ui

import android.app.Application
import com.russier.laurent.injection.ComponentAccessor
import com.russier.laurent.injection.DaggerDataComponent
import com.russier.laurent.injection.DaggerDomainComponent
import com.russier.laurent.injection.DomainComponent

class MyApplication : Application(), ComponentAccessor {

    private lateinit var domainComponent: DomainComponent

    override fun onCreate() {
        super.onCreate()

        domainComponent = DaggerDomainComponent
            .builder()
            .dataComponent(DaggerDataComponent.create())
            .build()
    }

    override fun domainComponent(): DomainComponent {
        return domainComponent
    }
}
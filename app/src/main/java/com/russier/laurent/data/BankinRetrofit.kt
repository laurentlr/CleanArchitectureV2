package com.russier.laurent.data

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class BankinRetrofit(baseUrl: String) {
    val retrofit: Retrofit = Retrofit
        .Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
}
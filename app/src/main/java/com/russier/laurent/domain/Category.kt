package com.russier.laurent.domain

data class Category(
    val id: String,
    val name: String,
    val parentId: String? = null
)
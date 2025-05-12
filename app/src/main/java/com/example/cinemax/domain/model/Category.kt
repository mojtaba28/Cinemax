package com.example.cinemax.domain.model

import androidx.annotation.Keep

 class Category: ArrayList<Category.CategoryItem>(){
    @Keep
    data class CategoryItem(
        val id: String?,
        val name: String?,

    )
}

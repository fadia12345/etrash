package com.dicoding.etrash.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dicoding.etrash.R

data class Category(
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int
)

enum class CategoryType {
    LOCATION,
    SCAN,
    RECYCLE,
    TRANSACTION
}

fun getCategory(categoryType: CategoryType): Category {
    return when (categoryType) {
        CategoryType.LOCATION -> Category(R.drawable.ic_location, R.string.category_location)
        CategoryType.SCAN -> Category(R.drawable.ic_scan, R.string.category_scan)
        CategoryType.RECYCLE -> Category(R.drawable.ic_recycle, R.string.category_recycle)
        CategoryType.TRANSACTION -> Category(R.drawable.ic_transaction, R.string.category_transaction)
    }
}
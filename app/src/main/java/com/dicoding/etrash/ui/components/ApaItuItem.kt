package com.dicoding.etrash.ui.components

import com.dicoding.etrash.R

data class ApaItuItem(
    val image: Int,
    val title: String

)

val dummyApaItuItem = listOf(
    ApaItuItem(R.drawable.untitled, "Apa Itu E-Trash?"),
    ApaItuItem(R.drawable.untitled, "Apa Itu Sampah?"),
    ApaItuItem(R.drawable.untitled, "Apa Itu Reduce-Reuse-Recycle?"),
    ApaItuItem(R.drawable.untitled, "Apa itu Uang?"),
)


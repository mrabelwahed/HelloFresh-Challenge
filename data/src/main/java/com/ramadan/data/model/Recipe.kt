package com.ramadan.data.model


data class Recipe(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    var favorite: Boolean
)
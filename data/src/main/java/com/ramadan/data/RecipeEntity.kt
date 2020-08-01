package com.ramadan.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "description")
    val description:String,
    @ColumnInfo(name = "image")
    val image:String,
    @ColumnInfo(name = "favorite")
    var favorite:Boolean
    )
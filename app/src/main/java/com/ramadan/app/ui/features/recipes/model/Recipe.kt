package com.ramadan.app.ui.features.recipes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Recipe(val id: String,val name : String, val description:String , val image:String, var favorite: Boolean)

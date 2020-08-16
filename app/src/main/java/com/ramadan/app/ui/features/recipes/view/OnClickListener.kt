package com.ramadan.app.ui.features.recipes.view

import com.ramadan.app.ui.features.recipes.model.Recipe

interface OnClickListener {
    fun onClick(recipe: Recipe)
}
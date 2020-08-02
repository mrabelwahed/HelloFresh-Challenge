package com.ramadan.app.robot

import com.ramadan.app.R

fun recipes(func: RecipesRobot.() -> Unit) = RecipesRobot().apply { func() }

class RecipesRobot : BaseRobot() {
    fun clickRecipeItem(position: Int) = clickListItem(R.id.recipesRecyclerView, position)
    fun checkRecipesListDisplayed() = matchDisplay(R.id.recipesRecyclerView)
    fun scrollTo(position: Int) = scrollTo(R.id.recipesRecyclerView, position)
}
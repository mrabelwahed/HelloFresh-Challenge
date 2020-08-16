package com.ramadan.app.ui.features.recipes.mapper

import com.ramadan.domain.model.Recipe
import com.ramadan.app.ui.features.recipes.model.Recipe as RecipeUI


object RecipeMapper {
     fun transform(recipe: Recipe):RecipeUI {
        return RecipeUI(recipe.id,recipe.name,recipe.description,recipe.image,recipe.favorite)
    }

    fun transform(recipesCollection: Collection<Recipe>): List<RecipeUI > {
        val recipeList = mutableListOf<RecipeUI >()
        for (recipe in recipesCollection) {
            val model = transform(recipe)
            if (model != null) {
                recipeList.add(model)
            }
        }
        return recipeList
    }

    fun transformToDomainRecipe(recipe: RecipeUI): Recipe {
       return Recipe(recipe.id,recipe.name,recipe.description,recipe.image,recipe.favorite)
    }
}
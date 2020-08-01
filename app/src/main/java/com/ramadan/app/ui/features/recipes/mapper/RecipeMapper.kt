package com.ramadan.app.ui.features.recipes.mapper

import com.ramadan.domain.model.Recipe


object RecipeMapper {
     fun transform(recipe: Recipe): com.ramadan.app.ui.features.recipes.model.Recipe {
        return com.ramadan.app.ui.features.recipes.model.Recipe(recipe.id,recipe.name,recipe.description,recipe.image,recipe.favorite)
    }

    fun transform(recipesCollection: Collection<Recipe>): List<com.ramadan.app.ui.features.recipes.model.Recipe > {
        val recipeList = mutableListOf<com.ramadan.app.ui.features.recipes.model.Recipe >()
        for (recipe in recipesCollection) {
            val model = transform(recipe)
            if (model != null) {
                recipeList.add(model)
            }
        }
        return recipeList
    }

    fun transformToDomainRecipe(recipe: com.ramadan.app.ui.features.recipes.model.Recipe): Recipe {
       return Recipe(recipe.id,recipe.name,recipe.description,recipe.image,recipe.favorite)
    }
}
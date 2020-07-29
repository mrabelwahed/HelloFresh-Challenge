import com.ramadan.data.model.Recipe

object RecipeMapper {
    private fun transform(recipe: Recipe): com.ramadan.domain.model.Recipe {
        return com.ramadan.domain.model.Recipe(recipe.id,recipe.name,recipe.description,recipe.image)
    }

    fun transform(recipesCollection: Collection<Recipe>): List<com.ramadan.domain.model.Recipe> {
        val recipeList = mutableListOf<com.ramadan.domain.model.Recipe>()
        for (recipe in recipesCollection) {
            val model = transform(recipe)
            if (model != null) {
                recipeList.add(model)
            }
        }
        return recipeList
    }
}
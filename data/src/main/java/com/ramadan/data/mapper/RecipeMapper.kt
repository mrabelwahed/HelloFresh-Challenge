import com.ramadan.data.RecipeEntity
import com.ramadan.data.model.Recipe

object RecipeMapper {
    private fun transform(recipe: Recipe): com.ramadan.domain.model.Recipe {
        return com.ramadan.domain.model.Recipe(recipe.id,recipe.name,recipe.description,recipe.image,recipe.favorite)
    }

     fun transformLocalRecipe(recipe: RecipeEntity): com.ramadan.domain.model.Recipe {
        return com.ramadan.domain.model.Recipe(recipe.id,recipe.name,recipe.description,recipe.image,recipe.favorite)
    }

     fun transformToEntity(recipe: com.ramadan.domain.model.Recipe): RecipeEntity {
        return RecipeEntity(recipe.id,recipe.name,recipe.description,recipe.image,recipe.favorite)
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

    fun transformLocalRecipes(recipesCollection: Collection<RecipeEntity>): List<com.ramadan.domain.model.Recipe> {
        val recipeList = mutableListOf<com.ramadan.domain.model.Recipe>()
        for (recipe in recipesCollection) {
            val model = transformLocalRecipe(recipe)
            if (model != null) {
                recipeList.add(model)
            }
        }
        return recipeList
    }

    fun transformToEntityCollection(recipesCollection: Collection<com.ramadan.domain.model.Recipe>): List<RecipeEntity> {
        val recipeList = mutableListOf<RecipeEntity>()
        for (recipe in recipesCollection) {
            val model = transformToEntity(recipe)
            if (model != null) {
                recipeList.add(model)
            }
        }
        return recipeList
    }
}
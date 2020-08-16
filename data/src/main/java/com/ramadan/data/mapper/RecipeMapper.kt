import com.ramadan.data.RecipeEntity
import com.ramadan.data.model.Recipe
import com.ramadan.domain.model.Recipe as RecipeDomain

object RecipeMapper {
    private fun transform(recipe: Recipe): RecipeDomain {
        return RecipeDomain(recipe.id,recipe.name,recipe.description,recipe.image,recipe.favorite)
    }

     fun transformLocalRecipe(recipe: RecipeEntity): RecipeDomain {
        return RecipeDomain(recipe.id,recipe.name,recipe.description,recipe.image,recipe.favorite)
    }

     fun transformToEntity(recipe: RecipeDomain): RecipeEntity {
        return RecipeEntity(recipe.id,recipe.name,recipe.description,recipe.image,recipe.favorite)
    }

    fun transform(recipesCollection: Collection<Recipe>): List<RecipeDomain> {
        val recipeList = mutableListOf<RecipeDomain>()
        for (recipe in recipesCollection) {
            val model = transform(recipe)
            if (model != null) {
                recipeList.add(model)
            }
        }
        return recipeList
    }

    fun transformLocalRecipes(recipesCollection: Collection<RecipeEntity>): List<RecipeDomain> {
        val recipeList = mutableListOf<RecipeDomain>()
        for (recipe in recipesCollection) {
            val model = transformLocalRecipe(recipe)
            if (model != null) {
                recipeList.add(model)
            }
        }
        return recipeList
    }

    fun transformToEntityCollection(recipesCollection: Collection<RecipeDomain>): List<RecipeEntity> {
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
package com.ramadan.app.ui.features.recipes.view

import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ramadan.app.robot.recipes
import com.ramadan.app.ui.features.recipedetails.view.RecipeDetailsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipesActivityTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(RecipesActivity::class.java)

    @Test
    fun should_launch_RecipesActivity() {
        recipes {
            checkRecipesListDisplayed()
        }
    }

    @Test
    fun should_open_details_screen_when_click_recipe_item() {
        recipes {
            checkRecipesListDisplayed()
            clickRecipeItem(0)
            intended(hasComponent(RecipeDetailsActivity::class.java?.name))
        }
    }

    @Test
    fun should_scroll_to_the_end_of_recipes_list() {
        recipes {
            checkRecipesListDisplayed()
            val count = intentsTestRule.activity.recipesAdapter.itemCount
            scrollTo(count - 1)
        }
    }
}
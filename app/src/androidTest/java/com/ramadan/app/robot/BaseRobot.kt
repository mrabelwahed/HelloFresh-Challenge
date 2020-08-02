package com.ramadan.app.robot

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId


open class BaseRobot {

    private fun recyclerview(resId: Int): ViewInteraction = onView(withId(resId))

    private fun matchDisplay(viewInteraction: ViewInteraction): ViewInteraction = viewInteraction
        .check(ViewAssertions.matches(isDisplayed()))

    fun matchDisplay(resId: Int): ViewInteraction = matchDisplay(recyclerview(resId))

    fun clickListItem(listRes: Int, position: Int) {
        recyclerview(listRes).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
    }

    fun scrollTo(listRes: Int, position: Int) {
        onView(withId(listRes)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position)
        )
    }

}
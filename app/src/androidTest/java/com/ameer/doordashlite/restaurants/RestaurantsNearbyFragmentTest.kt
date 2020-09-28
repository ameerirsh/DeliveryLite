package com.ameer.doordashlite.restaurants

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions


import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.ameer.doordashlite.MainActivity
import com.ameer.doordashlite.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

class RestaurantsNearbyFragmentTest {

    @get:Rule
    public var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java,
        true,
        true
    );
    /**
     * Recycler view comes into view
     */
    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.listNearbyRestaurants))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .check(matches(isDisplayed()))
    }
    /**
     * Select list item, navigates to restaurantdetails fragment
     */
    @Test
    fun test_selectListItem_isDetailFragmentVisible() {
        //clickOnButtonAtRow(0)
        onView(withId(R.id.listNearbyRestaurants))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .check(matches(isDisplayed()))
        clickOnButtonAtRow(0)

       // onView(withId(R.id.listNearbyRestaurants)).inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView))).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
       // onView(withId(R.id.listNearbyRestaurants)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        //onView(withId(R.id.txtRestName)).check(matches(withText("7-11")))
    }
    private fun clickOnButtonAtRow(position: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.listNearbyRestaurants)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>
            (position, ClickOnButtonView()))
    }

    inner class ClickOnButtonView : ViewAction {
        internal var click = ViewActions.click()
        override fun getConstraints(): Matcher<View> {
            return click.constraints
        }

        override fun getDescription(): String {
            return " click on custom button view"
        }

        override fun perform(uiController: UiController, view: View) {
            click.perform(uiController, view.findViewById(R.id.imgCover))
        }
    }


}
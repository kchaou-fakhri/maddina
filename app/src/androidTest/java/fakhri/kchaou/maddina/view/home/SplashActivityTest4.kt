package fakhri.kchaou.maddina.view.home


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*

import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import fakhri.kchaou.maddina.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
// WARNING: Espresso Test Recorder was paused during recording.
// The generated test may be missing actions which might lead to unexpected behavior.
class SplashActivityTest4 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun splashActivityTest4() {
        val appCompatButton = onView(
            allOf(
                withId(R.id.go_to_signup), withText("????? ????"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.TableLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.email),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.email_label),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("test2025@test.com"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.username),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.userame_label),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("test"), closeSoftKeyboard())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.password),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.password_label),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("12345678@"), closeSoftKeyboard())

        val appCompatAutoCompleteTextView = onView(
            allOf(
                withId(R.id.menu),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.label_menu),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatAutoCompleteTextView.perform(click())



        val appCompatButton2 = onView(
            allOf(
                withId(R.id.btn_sign), withText("????? ????"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.auth_fragment),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.email),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.label_email),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("test2025@test.com"), closeSoftKeyboard())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.password),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.label_password),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(replaceText("12345678@"), closeSoftKeyboard())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.btn_login), withText("????? ??????"),
                childAtPosition(
                    allOf(
                        withId(R.id.fragment_login),
                        childAtPosition(
                            withId(R.id.auth_fragment),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        val cellImageView = onView(
            allOf(
                withId(com.etebarian.meowbottomnavigation.R.id.iv),
                childAtPosition(
                    allOf(
                        withId(com.etebarian.meowbottomnavigation.R.id.fl),
                        childAtPosition(
                            withClassName(`is`("com.etebarian.meowbottomnavigation.MeowBottomNavigationCell")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cellImageView.perform(click())

        val cellImageView2 = onView(
            allOf(
                withId(com.etebarian.meowbottomnavigation.R.id.iv),
                childAtPosition(
                    allOf(
                        withId(com.etebarian.meowbottomnavigation.R.id.fl),
                        childAtPosition(
                            withClassName(`is`("com.etebarian.meowbottomnavigation.MeowBottomNavigationCell")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cellImageView2.perform(click())

        val cellImageView3 = onView(
            allOf(
                withId(com.etebarian.meowbottomnavigation.R.id.iv),
                childAtPosition(
                    allOf(
                        withId(com.etebarian.meowbottomnavigation.R.id.fl),
                        childAtPosition(
                            withClassName(`is`("com.etebarian.meowbottomnavigation.MeowBottomNavigationCell")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cellImageView3.perform(click())

        val cellImageView4 = onView(
            allOf(
                withId(com.etebarian.meowbottomnavigation.R.id.iv),
                childAtPosition(
                    allOf(
                        withId(com.etebarian.meowbottomnavigation.R.id.fl),
                        childAtPosition(
                            withClassName(`is`("com.etebarian.meowbottomnavigation.MeowBottomNavigationCell")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cellImageView4.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.search),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.home_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(pressImeActionButton())

        val recyclerView = onView(
            allOf(
                withId(R.id.list_users),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )


        val materialButton = onView(
            allOf(
                withId(R.id.add_friend), withText("??????"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    7
                )
            )
        )
        materialButton.perform(scrollTo(), click())

        pressBack()

        val cellImageView5 = onView(
            allOf(
                withId(com.etebarian.meowbottomnavigation.R.id.iv),
                childAtPosition(
                    allOf(
                        withId(com.etebarian.meowbottomnavigation.R.id.fl),
                        childAtPosition(
                            withClassName(`is`("com.etebarian.meowbottomnavigation.MeowBottomNavigationCell")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cellImageView5.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}

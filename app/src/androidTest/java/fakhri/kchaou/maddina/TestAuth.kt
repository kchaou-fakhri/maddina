package fakhri.kchaou.maddina

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import fakhri.kchaou.maddina.view.auth.LoginActivity
import fakhri.kchaou.maddina.view.auth.LoginFragment
import fakhri.kchaou.maddina.view.home.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class TestAuth {



    @Test
    fun testLogin(){
       val scenario = launchFragmentInContainer<LoginFragment>(
           initialState = Lifecycle.State.INITIALIZED
       )

        scenario.moveToState(Lifecycle.State.RESUMED)
    }
}
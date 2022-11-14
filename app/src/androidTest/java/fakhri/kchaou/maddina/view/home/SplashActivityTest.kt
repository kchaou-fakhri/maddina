package fakhri.kchaou.maddina.view.home


import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches


import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.view.auth.LoginActivity
import org.hamcrest.Matchers
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

//    @Rule
//    @JvmField
//    var mActivityScenarioRule = ActivityScenarioRule(SplashActivity::class.java)



    @Test
    fun verifyName() {

        val activityScenario = ActivityScenario.launch(SplashActivity::class.java)
        onView(withId(R.id.app_name_sp))
            .check(matches(withText(R.string.app_name)))
    }



    @Test
    fun splashActivityTest() {


        val activityScenario = ActivityScenario.launch(SplashActivity::class.java)
        Thread.sleep(4000)

//        val appCompatButton = onView(
//            Matchers.allOf(
//                withId(R.id.go_to_signup), withId(R.id.go_to_signup),
//
//                        withClassName(Matchers.`is`("android.widget.TableLayout")),
//
//
//                isDisplayed()
//            )
//        )
//        appCompatButton.perform(click())







        val textInputEditText = onView(
            Matchers.allOf(
                withId(R.id.email),
                Matchers.allOf(
                    withId(R.id.email), withHint("البريد الإلكتروني"),

                    isDisplayed()
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(
            ViewActions.typeText("test2022@test.com"),
            ViewActions.closeSoftKeyboard()
        )

        val textInputPassword = onView(
            Matchers.allOf(
                withId(R.id.password),
                Matchers.allOf(
                    withId(R.id.password), withHint("كلمة المرور"),

                    isDisplayed()
                ),
                isDisplayed()
            )
        )
        textInputPassword.perform(
            ViewActions.typeText("12345678@"),
            ViewActions.closeSoftKeyboard()
        )


        Thread.sleep(2000)
       onView(
            Matchers.allOf(
                withId(R.id.btn_login), withText(R.string.txt_login),

                isDisplayed()
            )
        )


  .perform(click())


        Thread.sleep(7000)




            val circleImageView = onView(
                Matchers.allOf(
                    withId(R.id.username),
                    withText("فخري كشو"),
                    isDisplayed()
                )
            )


            circleImageView.perform(click())

        Thread.sleep(2000)


        onView(withId( R.id.edit_profil))
            .perform(click())
        Thread.sleep(2000)
        onView(withId( R.id.edit_user_info))
            .perform(click())
        Thread.sleep(2000)

        pressBack()
        Thread.sleep(2000)
        pressBack()
        Thread.sleep(2000)







    }

    @Test
    fun createPost(){


        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        Thread.sleep(4000)
        onView(withId(R.id.create_post))
            .perform(click())

        onView(withId(R.id.post_text))
            .perform(
            ViewActions.typeText(
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries"),
            ViewActions.closeSoftKeyboard()
        )


        Thread.sleep(2000)

       onView(withId(R.id.btn_create))

        Thread.sleep(4000)

    }






}




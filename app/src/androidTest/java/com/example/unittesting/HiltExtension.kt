package com.example.unittesting

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi

/*
    Lambda is a simplified representation of function that makes
    the code lines lesser.
    When using lambdas, the extra memory and extra virtual method call introduce
    some runtime overhead.

    With inline function, the compiler just inlines the function body which means
    just take the code of the function then substitute it with
    no extra object allocation or extra virtual method call.

 */

/*
    Kotlin erases the generic type(T) information at runtime,
    so to compile that generic type use "reified"

 */

/** LaunchFragmentInContainer<YourFragment>(args){..} */
@ExperimentalCoroutinesApi
inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
    fragmentFactory: FragmentFactory? = null,
    crossinline action: T.() -> Unit = {}
) {
    val mainActivityIntent = Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            HiltActivity::class.java
        )
    ).putExtra(
        "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY",
        themeResId)

    /*
    *   Define my custom activity I want to launch.
    *   in onActivity(), I get the reference for that activity then
    *   attach a fragment inside of it.
    *
    *   To attach a fragment, I need a factory to build that fragment [note: which fragment is defined by T]
    *   Check if it's not null then begin transaction [start launch fragment inside activity]
    */
    ActivityScenario.launch<HiltActivity>(mainActivityIntent).onActivity { activity ->

        fragmentFactory?.let {
            activity.supportFragmentManager.fragmentFactory = it
        }
        val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            Preconditions.checkNotNull(T::class.java.classLoader),
            T::class.java.name
        )
        fragment.arguments = fragmentArgs

        activity.supportFragmentManager.beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()

        (fragment as T).action()
    }

}
package com.example.unittesting

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/*
    Set default configuration of testInstrumentationRunner 'androidx.test.runner.AndroidJUnitRunner'
    to this HiltTestRunner in gradle.
    Then remove @RunWith(AndroidJUnit4::class) because we already have our own runner(HiltTestRunner)
    in every class of android component.
    Specify hilt to inject dependency for test class with @HiltAndroidTest similar to @AndroidEntryPoint for
    AndroidComponent.
*/
class HiltTestRunner: AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
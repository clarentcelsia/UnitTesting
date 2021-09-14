package com.example.unittesting

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/*
    Usually, with hilt, if we want to inject our dependencies  into
    fragments, we annotate them with @AndroidEntryPoint and the activity
    which hosts those fragments as well.

    But for this testing case, to test a fragment we need a scenario
    called "Fragment Scenario" that launches an empty activity as their host.

    Nothing's problem if I don't use hilt.
    The problem is I use hilt then that empty activity that comes from library isn't
    annotated with @AndroidEntryPoint, and it caused a crash/error.

    So to solve the problem for this test case, I need my own custom activity that
    I created in debug directory (which only intended for use during development).

    note: to launch this custom activity, I need to copy AndroidManifest to this directory
    because this custom activity just for testing,
    I don't want to include it in real manifest by default.

 */

@AndroidEntryPoint
class HiltActivity: AppCompatActivity() {
}
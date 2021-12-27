package com.pepdoesthings.marvelchars.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // LIFECYCLE

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Timber.v("Lifecycle: ${javaClass.simpleName}.onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Timber.v("Lifecycle: ${javaClass.simpleName}.onStart()")
    }

    override fun onResume() {
        super.onResume()
        Timber.v("Lifecycle: ${javaClass.simpleName}.onResume()")
    }

    override fun onPause() {
        super.onPause()
        Timber.v("Lifecycle: ${javaClass.simpleName}.onPause()")
    }

    override fun onStop() {
        super.onStop()
        Timber.v("Lifecycle: ${javaClass.simpleName}.onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.v("Lifecycle: ${javaClass.simpleName}.onDestroy()")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
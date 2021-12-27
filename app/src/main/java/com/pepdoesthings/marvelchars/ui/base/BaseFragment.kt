package com.pepdoesthings.marvelchars.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // LIFECYCLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("Lifecycle: ${javaClass.simpleName}.onCreate()")
    }

    // method  onCreateView will be overridden anyway.

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.v("Lifecycle: ${javaClass.simpleName}.onViewCreated()")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Timber.v("Lifecycle: ${javaClass.simpleName}.onViewStateRestored()")
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.v("Lifecycle: ${javaClass.simpleName}.onSaveInstanceState()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.v("Lifecycle: ${javaClass.simpleName}.onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.v("Lifecycle: ${javaClass.simpleName}.onDestroy()")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
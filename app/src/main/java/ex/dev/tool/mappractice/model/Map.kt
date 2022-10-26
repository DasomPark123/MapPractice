package ex.dev.tool.mappractice.model

import android.os.Bundle

interface Map {
    fun onCreate(saveInstanceState : Bundle?)
    fun onSaveInstanceState(outState : Bundle)
    fun onResume()
    fun onPause()
    fun onDestroy()
    fun onLowMemory()
    fun setZoom()
    fun setInitialLocation()
    fun moveToCurrentLocation()
    fun setMarker()
}
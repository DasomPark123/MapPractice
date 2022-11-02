package ex.dev.tool.mappractice.model

import android.os.Bundle

interface Map {
    fun onCreate(saveInstanceState : Bundle?)
    fun onSaveInstanceState(outState : Bundle)
    fun onResume()
    fun onPause()
    fun onDestroy()
    fun onLowMemory()
    fun getMapAsync()
    fun setZoom(maxZoom : Double, minZoom : Double)
    fun moveLocation(latitude : Double, longitude : Double)
    fun setMarker(latitude : Double, longitude : Double)
}
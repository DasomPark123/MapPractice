package ex.dev.tool.mappractice.model

import android.os.Bundle
import android.view.View
import ex.dev.tool.mappractice.databinding.FragmentMapBinding

class GoogleMap(val binding : FragmentMapBinding) : Map {

    init {
        binding.googleMapView.visibility = View.VISIBLE
    }

    override fun onCreate(saveInstanceState: Bundle?) {
        binding.googleMapView.onCreate(saveInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.googleMapView.onSaveInstanceState(outState)
    }

    override fun onResume() {
        binding.googleMapView.onResume()
    }

    override fun onPause() {
        binding.googleMapView.onPause()
    }

    override fun onDestroy() {
        binding.googleMapView.onDestroy()
    }

    override fun onLowMemory() {
        binding.googleMapView.onLowMemory()
    }

    override fun setZoom() {
        TODO("Not yet implemented")
    }

    override fun setInitialLocation() {
        TODO("Not yet implemented")
    }

    override fun moveToCurrentLocation() {
        TODO("Not yet implemented")
    }

    override fun setMarker() {
        TODO("Not yet implemented")
    }
}
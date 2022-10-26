package ex.dev.tool.mappractice.model

import android.os.Bundle
import android.view.View
import ex.dev.tool.mappractice.databinding.FragmentMapBinding

class NaverMap(val binding : FragmentMapBinding) : Map {

    init {
        binding.naverMapView.visibility = View.VISIBLE
    }

    override fun onCreate(saveInstanceState: Bundle?) {
        binding.naverMapView.onCreate(saveInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.naverMapView.onSaveInstanceState(outState)
    }

    override fun onResume() {
        binding.naverMapView.onResume()
    }

    override fun onPause() {
        binding.naverMapView.onPause()
    }

    override fun onDestroy() {
        binding.naverMapView.onDestroy()
    }

    override fun onLowMemory() {
        binding.naverMapView.onLowMemory()
    }

    override fun setZoom() {

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
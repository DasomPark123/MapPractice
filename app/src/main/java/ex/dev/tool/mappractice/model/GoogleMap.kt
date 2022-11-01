package ex.dev.tool.mappractice.model

import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ex.dev.tool.mappractice.databinding.FragmentMapBinding

class GoogleMap(private val binding : FragmentMapBinding) : Map, OnMapReadyCallback {

    private lateinit var googleMap : GoogleMap

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

    override fun getMapAsync() {
        binding.googleMapView.getMapAsync(this)
    }

    override fun setZoom() {
        TODO("Not yet implemented")
    }

    override fun setInitialLocation() {
        TODO("Not yet implemented")
    }

    override fun moveLocation() {
        TODO("Not yet implemented")
    }

    override fun setMarker() {
        val latLng = LatLng(37.56, 126.97)
        val markerOption = MarkerOptions()
        markerOption.apply {
            position(latLng)
            title("Seoul")
            snippet("Capital of Korea")
        }
        googleMap.addMarker(markerOption)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }
}
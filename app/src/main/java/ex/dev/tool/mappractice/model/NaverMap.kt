package ex.dev.tool.mappractice.model

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import ex.dev.tool.mappractice.databinding.FragmentMapBinding

class NaverMap(val binding : FragmentMapBinding) : Map, com.naver.maps.map.OnMapReadyCallback {

    private lateinit var naverMap : com.naver.maps.map.NaverMap

    init {
        binding.naverMapView.visibility = View.VISIBLE
        binding.naverMapView.getMapAsync(this)
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
        val latLng = com.naver.maps.geometry.LatLng(37.56, 126.97)
        val marker = Marker()
        marker.apply {
            position = latLng
            map = naverMap
            icon = MarkerIcons.BLACK
            iconTintColor = Color.BLACK
        }

    }

    override fun onMapReady(naverMap : com.naver.maps.map.NaverMap) {
        this.naverMap = naverMap
    }
}
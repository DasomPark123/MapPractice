package ex.dev.tool.mappractice.model

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import ex.dev.tool.mappractice.databinding.FragmentMapBinding

class NaverMap(private val fragment : Fragment, private val binding : FragmentMapBinding) : Map, com.naver.maps.map.OnMapReadyCallback {

    private lateinit var naverMap : com.naver.maps.map.NaverMap
    private lateinit var locationSource: FusedLocationSource

    companion object {
        val TAG = com.naver.maps.map.NaverMap::class.simpleName
    }

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

    override fun moveLocation() {
        TODO("Not yet implemented")
    }

    override fun setLocationTrackingModel(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            Log.d(TAG,"locationSource : ${locationSource.isActivated}")
            if(!locationSource.isActivated) {
                Log.d(TAG,"none")
                naverMap.locationTrackingMode = LocationTrackingMode.None
            } else {
                Log.d(TAG,"follow")
                naverMap.locationTrackingMode = LocationTrackingMode.Follow
            }
        }
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

        val uiSetting = naverMap.uiSettings
        uiSetting.isLocationButtonEnabled = true

        locationSource = FusedLocationSource(fragment.activity as Activity, ex.dev.tool.mappractice.view.map.MapFragment.LOCATION_PERMISSION_REQUEST_CODE)
        naverMap.locationSource = locationSource
    }
}
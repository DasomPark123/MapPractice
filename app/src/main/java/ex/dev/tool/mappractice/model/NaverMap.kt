package ex.dev.tool.mappractice.model

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import ex.dev.tool.mappractice.databinding.FragmentMapBinding
import kotlin.math.min

class NaverMap(private val fragment : Fragment, private val binding : FragmentMapBinding) : Map, com.naver.maps.map.OnMapReadyCallback {

    private lateinit var naverMap : com.naver.maps.map.NaverMap
    private lateinit var locationSource: FusedLocationSource

    private var marker = Marker()

    companion object {
        val TAG = com.naver.maps.map.NaverMap::class.simpleName
    }

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

    override fun getMapAsync() {
        binding.naverMapView.getMapAsync(this)
    }

    /* 줌 제어 */
    override fun setZoom(maxZoom: Double, minZoom: Double) {
        naverMap.maxZoom = maxZoom
        naverMap.minZoom = minZoom
    }

    /* 인지값의 위도, 경도 위치로 화면 이동 */
    override fun moveLocation(latitude : Double, longitude : Double) {
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(latitude, longitude))
        naverMap.moveCamera(cameraUpdate)
    }

    /* 원하는 위도, 경도에 마커 찍기 */
    override fun setMarker(latitude: Double, longitude: Double) {
        val latLng = LatLng(latitude, longitude)
        marker.apply {
            position = latLng
            map = naverMap
            icon = MarkerIcons.BLACK
            iconTintColor = Color.BLACK
        }
    }

    override fun onMapReady(naverMap : com.naver.maps.map.NaverMap) {
        this.naverMap = naverMap

        /* 현재위치 버튼 활성화 */
        val uiSetting = naverMap.uiSettings
        uiSetting.isLocationButtonEnabled = true

        /* 위치 tracker 설정 */
        locationSource = FusedLocationSource(fragment, ex.dev.tool.mappractice.view.map.MapFragment.LOCATION_PERMISSION_REQUEST_CODE)
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        setZoom(18.0, 10.0)

        naverMap.addOnCameraChangeListener { reason, animated ->
            val cameraPosition = naverMap.cameraPosition.target
            setMarker(cameraPosition.latitude, cameraPosition.longitude)
        }
    }
}
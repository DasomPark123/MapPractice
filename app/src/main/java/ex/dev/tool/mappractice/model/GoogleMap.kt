package ex.dev.tool.mappractice.model

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ex.dev.tool.mappractice.databinding.FragmentMapBinding

class GoogleMap(private val fragment : Fragment, private val binding : FragmentMapBinding) : Map, OnMapReadyCallback {

    private lateinit var googleMap : GoogleMap

    private var marker : Marker? = null
    private val markerOption = MarkerOptions()

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

    override fun setZoom(maxZoom: Double, minZoom: Double) {
        googleMap.setMaxZoomPreference(maxZoom.toFloat())
        googleMap.setMinZoomPreference(maxZoom.toFloat())
    }

    override fun moveLocation(latitude : Double, longitude : Double) {
        val cameraUpdate = CameraUpdateFactory.newLatLng(LatLng(latitude, longitude))
        googleMap.moveCamera(cameraUpdate)
    }

    override fun setMarker(latitude: Double, longitude: Double) {
        marker?.let { it.remove() }
        val latLng = LatLng(latitude, longitude)
        markerOption.apply {
            position(latLng)
            title("Seoul")
            snippet("Capital of Korea")
        }
        marker = googleMap.addMarker(markerOption)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        val uiSetting = googleMap.uiSettings
        uiSetting.isMyLocationButtonEnabled = true

        if(ContextCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(fragment.requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==  PackageManager.PERMISSION_GRANTED) {
            googleMap.isMyLocationEnabled = true // It has to check location permissions explicit
        }

        setZoom(18.0, 10.0)

        googleMap.setOnCameraMoveListener {
            val cameraPosition = googleMap.cameraPosition.target
            setMarker(cameraPosition.latitude, cameraPosition.longitude)
        }
    }
}
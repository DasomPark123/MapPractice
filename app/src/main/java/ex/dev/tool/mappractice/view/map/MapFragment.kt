package ex.dev.tool.mappractice.view.map

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ex.dev.tool.mappractice.R
import ex.dev.tool.mappractice.databinding.FragmentMapBinding
import ex.dev.tool.mappractice.model.GoogleMap
import ex.dev.tool.mappractice.model.NaverMap
import ex.dev.tool.mappractice.utils.JAPAN
import ex.dev.tool.mappractice.utils.KOREA

class MapFragment : Fragment() {

   private lateinit var map : ex.dev.tool.mappractice.model.Map

   private lateinit var binding : FragmentMapBinding

    private val permissions = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    companion object {
        val TAG = MapFragment::class.simpleName
        const val LOCATION_PERMISSION_REQUEST_CODE = 0x1001
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val region = arguments?.getInt(MapActivity.EXTRA_REGION)
        Log.d(TAG, "region : $region")
        requestPermissions()
        map = createMap(region)
        map.onCreate(savedInstanceState)
        initView()
    }

    private fun checkPermission() : Boolean =
        ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions() {
        if(!checkPermission())
            requestPermissions(permissions, LOCATION_PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult( //It's deprecated. but we have to use this because of NAVER map library
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d(TAG,"onRequestPermissionResult : $requestCode")
        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            map.setLocationTrackingModel(requestCode, permissions, grantResults)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun createMap(region : Int?) = when(region) {
        KOREA ->  { NaverMap(this, binding) }
        JAPAN -> { GoogleMap(binding) }
        else -> { GoogleMap(binding) }
    }

    private fun initView() {
        binding.btnCurrentLocation.setOnClickListener { map.moveLocation() }
        binding.btnMarker.setOnClickListener { map.setMarker() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        map.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        map.onDestroy()
    }
}
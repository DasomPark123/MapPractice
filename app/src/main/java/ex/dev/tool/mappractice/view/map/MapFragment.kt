package ex.dev.tool.mappractice.view.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ex.dev.tool.mappractice.R
import ex.dev.tool.mappractice.databinding.FragmentMapBinding
import ex.dev.tool.mappractice.model.GoogleMap
import ex.dev.tool.mappractice.model.NaverMap
import ex.dev.tool.mappractice.utils.*

class MapFragment : Fragment() {

    private lateinit var map: ex.dev.tool.mappractice.model.Map

    private lateinit var binding: FragmentMapBinding

    private val permissions = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    private val multiPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
            var isAllGranted = true
            for (entry in map.entries) {
                if (!entry.value) {
                    isAllGranted = false
                    break
                }
            }
            checkPermissionGranted(isAllGranted)
        }

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
        map = createMap(region)
        map.onCreate(savedInstanceState)
        requestPermissions()
        initView()
    }

    private fun checkLocationPermission(): Boolean = requireActivity().hasPermissions(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    private fun requestPermissions() {
        if (!checkLocationPermission()) {
            multiPermissionLauncher.launch(permissions)
        } else {
            map.getMapAsync()
        }
    }

    private fun checkPermissionGranted(isAllGranted: Boolean) {
        if (isAllGranted) {
            map.getMapAsync()
        } else {
            activity?.finish()
        }
    }

    private fun createMap(region: Int?) = when (region) {
        KOREA -> {
            NaverMap(this, binding)
        }
        JAPAN -> {
            GoogleMap(this, binding)
        }
        else -> {
            GoogleMap(this, binding)
        }
    }

    private fun initView() {
        binding.btnCurrentLocation.setOnClickListener { }
        binding.btnMarker.setOnClickListener {  }
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
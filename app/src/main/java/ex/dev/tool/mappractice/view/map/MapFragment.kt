package ex.dev.tool.mappractice.view.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import ex.dev.tool.mappractice.R
import ex.dev.tool.mappractice.databinding.FragmentMapBinding
import ex.dev.tool.mappractice.model.GoogleMap
import ex.dev.tool.mappractice.model.NaverMap
import ex.dev.tool.mappractice.utils.JAPAN
import ex.dev.tool.mappractice.utils.KOREA

class MapFragment : Fragment() {

   private lateinit var map : ex.dev.tool.mappractice.model.Map

   private lateinit var binding : FragmentMapBinding

   companion object {
       val TAG = MapFragment::class.simpleName
       const val REQUEST_MAP_KEY = "REQUEST_MAP_KEY"
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
        map = when(region) {
            KOREA ->  { NaverMap(binding) }
            JAPAN -> { GoogleMap(binding) }
            else -> { GoogleMap(binding) }
        }
        map.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnCurrentLocation.setOnClickListener { map.moveToCurrentLocation() }
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
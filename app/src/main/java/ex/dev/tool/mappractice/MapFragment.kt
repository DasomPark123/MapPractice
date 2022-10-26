package ex.dev.tool.mappractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.naver.maps.geometry.LatLng
import ex.dev.tool.mappractice.databinding.FragmentMapBinding
import ex.dev.tool.mappractice.model.NaverMap

class MapFragment : Fragment() {

   private lateinit var map : ex.dev.tool.mappractice.model.Map

   private lateinit var binding : FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        map = NaverMap(binding)
        map.onCreate(savedInstanceState)
        initView()
        return binding.root
    }

    fun initView() {
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
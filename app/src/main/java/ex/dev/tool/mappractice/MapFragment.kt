package ex.dev.tool.mappractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.naver.maps.geometry.LatLng
import ex.dev.tool.mappractice.databinding.FragmentMapBinding

class MapFragment : Fragment(), OnMapReadyCallback{

    private lateinit var binding : FragmentMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //각 mapview 생명주기 호출
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        binding.googleMapView.onCreate(savedInstanceState)
        binding.googleMapView.getMapAsync(this)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.googleMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.googleMapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.googleMapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.googleMapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.googleMapView.onDestroy()
    }

    override fun onMapReady(p0: GoogleMap) {
        val seoul = LatLng(37.56, 126.97 )
    }
}
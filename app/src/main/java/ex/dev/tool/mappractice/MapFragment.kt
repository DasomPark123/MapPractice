package ex.dev.tool.mappractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import ex.dev.tool.mappractice.databinding.FragmentMapBinding

class MapFragment : Fragment(), OnMapReadyCallback{
    //각 mapview 생명주기 호출
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentMapBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
//        binding.googleMapView.getMapAsync(this)
        binding.googleMapView.onCreate(savedInstanceState)
        binding.googleMapView.getMapAsync(this)
        return binding.root
    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }
}
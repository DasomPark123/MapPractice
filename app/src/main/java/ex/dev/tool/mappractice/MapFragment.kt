package ex.dev.tool.mappractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ex.dev.tool.mappractice.databinding.FragmentMapBinding

class MapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentMapBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        return binding.root
    }
}
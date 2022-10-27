package ex.dev.tool.mappractice.view.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ex.dev.tool.mappractice.R
import ex.dev.tool.mappractice.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMapBinding

    companion object {
        const val EXTRA_REGION = "EXTRA_REGION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map)

        val region = intent.extras?.getInt(EXTRA_REGION)

        val bundle = Bundle()
        bundle.putInt(EXTRA_REGION, region!!)

        val fragmentMap = MapFragment()
        fragmentMap.arguments = bundle
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_map, fragmentMap)
    }
}
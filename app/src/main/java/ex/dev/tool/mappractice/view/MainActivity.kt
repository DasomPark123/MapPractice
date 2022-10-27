package ex.dev.tool.mappractice.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ex.dev.tool.mappractice.R
import ex.dev.tool.mappractice.databinding.ActivityMainBinding
import ex.dev.tool.mappractice.utils.GOOGLE
import ex.dev.tool.mappractice.utils.NAVER
import ex.dev.tool.mappractice.utils.startActivity
import ex.dev.tool.mappractice.view.map.MapActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btnNaverMap.setOnClickListener { startActivity<MapActivity>(MapActivity.EXTRA_REGION to NAVER) }
        binding.btnGoogleMap.setOnClickListener { startActivity<MapActivity>(MapActivity.EXTRA_REGION to GOOGLE) }
    }
}
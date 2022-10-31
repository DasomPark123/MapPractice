package ex.dev.tool.mappractice.view

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.gun0912.tedpermission.coroutine.TedPermission
import ex.dev.tool.mappractice.R
import ex.dev.tool.mappractice.databinding.ActivityMainBinding
import ex.dev.tool.mappractice.utils.JAPAN
import ex.dev.tool.mappractice.utils.KOREA
import ex.dev.tool.mappractice.utils.startActivity
import ex.dev.tool.mappractice.view.map.MapActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btnNaverMap.setOnClickListener { startActivity<MapActivity>(MapActivity.EXTRA_REGION to KOREA) }
        binding.btnGoogleMap.setOnClickListener { startActivity<MapActivity>(MapActivity.EXTRA_REGION to JAPAN) }
        requestLocationPermission()
    }

    private fun requestLocationPermission() {
        lifecycleScope.launch(Dispatchers.Main) {
            val permissionResult = withContext(lifecycleScope.coroutineContext) {
                TedPermission.create().setPermissions(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ).check()
            }
            if(!permissionResult.isGranted) finish()
        }
    }
}
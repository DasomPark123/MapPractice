package ex.dev.tool.mappractice.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf

inline fun <reified  T : AppCompatActivity> Context.startActivity (
    vararg extras : Pair<String, Any?>,
    intentAction : Intent . () -> Unit = {}
) {
    startActivity(Intent(this, T::class.java).apply {
        putExtras(bundleOf(*extras))
        intentAction()
    })
}

fun Context.hasPermissions(vararg permissions : String) = permissions.all { permission ->
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}
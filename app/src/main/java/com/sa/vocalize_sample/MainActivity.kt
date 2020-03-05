package com.sa.vocalize_sample

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sdk.vocalize.Vocalize

class MainActivity : AppCompatActivity() {

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPhonePermission()
        }
    }

    private fun getPhonePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                )
            ) {

            }

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                101
            )
        } else {
//            Vocalize.initialize(this@MainActivity, this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 101) {
            if (grantResults.size > 0 && grantResults.get(0) == PackageManager.PERMISSION_GRANTED) {
//                Vocalize.initialize(this@MainActivity, this)
            } else {
                getPhonePermission()
            }

            return
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Vocalize.reset()
    }
}
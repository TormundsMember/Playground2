package io.github.tormundsmember.locationtest

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    Dexter.withActivity(this)
        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        .withListener(object : PermissionListener {

          @SuppressLint("MissingPermission")
          override fun onPermissionGranted(response: PermissionGrantedResponse?) {
            val client = LocationServices.getFusedLocationProviderClient(this@MainActivity)
            client.lastLocation.addOnSuccessListener { location: Location? ->
              Log.d("", "")
            }
          }

          override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {

          }

          override fun onPermissionDenied(response: PermissionDeniedResponse?) {

          }

        }).check()

  }
}
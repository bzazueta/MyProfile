package com.monosoft.myprofile.presentation.screens.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.monosoft.myprofile.R
import com.monosoft.myprofile.databinding.ActivityMapsBinding
import java.io.IOException
import java.util.Locale

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMapClickListener,
    GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var  markerName : Marker
    lateinit var  markerNameLocation : Marker
    var clickMap : Boolean = false
    var city: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createMapFragment()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /*val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)*/
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMaps: GoogleMap) {
        if (googleMaps != null) {
            mMap = googleMaps
        }
        createMarker(25.6807167,-100.3233551)
        mMap.setOnMapClickListener(this)
        enableMyLocation()
        mMap.isTrafficEnabled=true
    }

    private fun createMarker(lat :Double,long: Double) {
        val favoritePlace = LatLng(lat,long)
        markerName = mMap.addMarker(MarkerOptions().position(favoritePlace).title("Title"))!!

        // map.addMarker(MarkerOptions().position(favoritePlace).title("Mi playa favorita!").draggable(true))
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(favoritePlace, 15f),
            4000,
            null
        )
    }

    private fun isPermissionsGranted() =
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        }else
        {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (!::mMap.isInitialized) return
        if (isPermissionsGranted()) {
            mMap.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    mMap.isMyLocationEnabled = true
                    return
                }

            }else{
                Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    override fun onMapClick(p0: LatLng) {
        markerName!!.remove()


        val geocoder: Geocoder
        val addresses: List<Address>?
        geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
        try {
            addresses = geocoder.getFromLocation(
                p0.latitude,
                p0.longitude,
                1
            )
            city = addresses!![0].getAddressLine(0)
            Toast.makeText(this@MapsActivity, city, Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        createMarkerLocation(p0.latitude,p0.longitude)
    }

    private fun createMarkerLocation(lat :Double,long: Double) {
        val favoritePlace = LatLng(lat,long)
//        VariablesGlobales.setLatitud(lat.toString())
//        VariablesGlobales.setLogitud(long.toString())
        if(clickMap==true)
        {
            markerNameLocation.remove()
        }
        markerNameLocation = mMap.addMarker(MarkerOptions().position(favoritePlace).title(city))!!
        //map.addMarker(MarkerOptions().position(favoritePlace).title("Mi playa favorita!").draggable(true))//18
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(favoritePlace, 15f),
            4000,
            null
        )
        clickMap=true
    }

    private fun createMapFragment() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT)
            .show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Current location:\n${p0.latitude} -- ${p0.longitude}", Toast.LENGTH_LONG)
            .show()
    }
}
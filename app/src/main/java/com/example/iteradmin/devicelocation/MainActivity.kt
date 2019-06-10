package com.example.iteradmin.devicelocation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button=findViewById<Button>(R.id.button)
        val txt=findViewById<TextView>(R.id.text)


        val fl=LocationServices.
                getFusedLocationProviderClient(this)

        button.setOnClickListener{

             if(ContextCompat.checkSelfPermission(this,android.Manifest.
                             permission.ACCESS_COARSE_LOCATION)==PackageManager.
                             PERMISSION_GRANTED)
            {
            fl.lastLocation.addOnSuccessListener { location: Location? ->
                if(location == null){
                    Toast.makeText(this,"Location Not Found",Toast.LENGTH_LONG).show()
                }else
                {
                    val s:String="Longitude : "+location.longitude + "\n Latitude : "+location.latitude
                    txt.text=s

                    Toast.makeText(this,"Location Found",Toast.LENGTH_LONG).show()
                }
            }


            } else{
               ActivityCompat.requestPermissions(this,
                       arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),100)
             }

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                          permissions: Array<out String>, grantResults: IntArray) {
           if(requestCode==100)
           {
               if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                   Toast.makeText(this,"Permission han been granted",Toast.LENGTH_LONG).show()
               }else{
                   Toast.makeText(this,"Permission denied",Toast.LENGTH_LONG).show()
               }
           }

    }
}

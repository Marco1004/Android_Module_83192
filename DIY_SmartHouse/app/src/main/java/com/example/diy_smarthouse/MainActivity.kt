package com.example.diy_smarthouse

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.custom_layout.*
import java.io.File

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roomb= findViewById<Button>(R.id.button)
        roomb.setOnClickListener{
            val intent= Intent(this, RoomsList::class.java)
            startActivity(intent)
        }

        val mapFrag= supportFragmentManager
        val map_buttom= findViewById<Button>(R.id.map_button)
        map_buttom.setOnClickListener {
            //val intent= Intent(this, MapsFragment::class.java)
            val ft = mapFrag.beginTransaction().replace(R.id.container, MapsFragment()).commit()
            //startActivity(intent)
        }

        val cameraButton= findViewById<Button>(R.id.camera_button)
        cameraButton.setOnClickListener {
            val intent= Intent(this, CamActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        //
    }

}
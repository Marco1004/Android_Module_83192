package com.example.diy_smarthouse

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton

import android.widget.SeekBar
import android.widget.TextView
import com.google.firebase.firestore.*


class DeviceList : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    lateinit var roomLight: Light
    lateinit var acUnit: AC
    lateinit var roomThermostat: Thermostat
    lateinit var room: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

        room= intent.getStringExtra("room").toString()
        getData()
    }

    fun getData(){
        db= FirebaseFirestore.getInstance()
        db.collection("Devices").get()
            .addOnSuccessListener { documents->
                for (dc in documents){
                    val device= dc["type"].toString()

                    if(device == "thermostat"){
                        roomThermostat= dc.toObject(Thermostat::class.java)
                        thermostatData()
                    }
                    if(device == "light"){
                        roomLight= dc.toObject(Light::class.java)
                        roomLight.id= dc.id
                        lightControl()
                    }
                    if(device == "ac"){
                        acUnit= dc.toObject(AC::class.java)
                        acUnit.id= dc.id
                        AC_control()
                    }
                }
            }
            .addOnFailureListener{ Exception ->
                Log.w(TAG, "Error getting documents: ", Exception)
            }
    }

    private fun lightControl(){
        var color: TextView = findViewById(R.id.color_value)
        var intensity: TextView= findViewById(R.id.intensity_value)
        val lightID= roomLight.id

        intensity.text= roomLight.intensity.toString()
        color.text= roomLight.color.toString()

        val intensityBar: SeekBar= findViewById(R.id.intensitySeekBar)
        intensityBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                intensity.text= progress.toString()
                var new_intensity= hashMapOf("intensity" to progress)
                db.collection("Devices").document("$lightID")
                    .set(new_intensity, SetOptions.merge())
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        val colorBar: SeekBar= findViewById(R.id.colorSeekBar)
        colorBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                color.text= progress.toString()
                var new_color= hashMapOf("color" to progress)
                db.collection("Devices").document("$lightID")
                    .set(new_color, SetOptions.merge())
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }
    private fun thermostatData(){
        //Log.d("Thermostat", "$roomThermostat")
        if (roomThermostat!= null) {
            var temp: TextView = findViewById(R.id.temp_value)
            temp.text = roomThermostat.temperature.toString()+ "ºC"

            var humd: TextView = findViewById(R.id.humidity_value)
            humd.text = roomThermostat.humidity.toString()+ "%"
        }
    }
    //@SuppressLint("WrongViewCast")
    private fun AC_control(){
        val ID= acUnit.id
        var temp: Int
        temp= acUnit.temperature!!
        var tempUp= findViewById<ImageButton>(R.id.temp_up)
        var tempDown= findViewById<ImageButton>(R.id.temp_down)
        var t: TextView= findViewById(R.id.set_temp)
        t.text= temp.toString()

        tempUp.setOnClickListener {
            temp= temp+ 1
            var newTemp= hashMapOf("temperature" to temp)
            db.collection("Devices").document("ID")
                .set(newTemp, SetOptions.merge())
            t.text= temp.toString()
        }
        tempDown.setOnClickListener {
            temp= temp- 1
            var newTemp= hashMapOf("temperature" to temp)
            db.collection("Devices").document("ID")
                .set(newTemp, SetOptions.merge())
            t.text= temp.toString()
        }
    }
}
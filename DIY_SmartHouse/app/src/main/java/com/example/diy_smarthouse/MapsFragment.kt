package com.example.diy_smarthouse

import android.content.ContentValues
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.*

class MapsFragment : Fragment() {
    private lateinit var db: FirebaseFirestore
    private lateinit var HomeList: ArrayList<LatLng>

    private val callback = OnMapReadyCallback { googleMap ->
        //getLocations()
        /*
        var loc: Location
        db= FirebaseFirestore.getInstance()
        db.collection("Houses").get()
            .addOnSuccessListener { documents->
                for (dc in documents){
                    loc= dc.toObject(Location::class.java)
                    val mark= LatLng(loc.latitude, loc.longitude)
                    googleMap.addMarker(MarkerOptions().position(mark).title("Marker in Sydney"))
                }
            }
            .addOnFailureListener{ Exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", Exception)
            }

         */

        val aveiro = LatLng(40.6391, -8.6548)
        googleMap.addMarker(MarkerOptions().position(aveiro).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(aveiro))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}
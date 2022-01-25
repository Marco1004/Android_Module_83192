package com.example.diy_smarthouse

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_rooms_list.*

class RoomsList : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var roomRecyclerView: RecyclerView
    private lateinit var roomArrayList: ArrayList<Room>
    private lateinit var myAdapter: RoomAdapter
    lateinit var selected_room: String
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms_list)

        val intent= Intent(this, DeviceList::class.java)
        roomRecyclerView= findViewById(R.id.roomlist)
        roomRecyclerView.layoutManager= GridLayoutManager(this, 2)
        roomRecyclerView.setHasFixedSize(true)

        roomArrayList= arrayListOf()
        myAdapter= RoomAdapter(roomArrayList)
        roomRecyclerView.adapter= myAdapter
        myAdapter.setOnClickListener(object: RoomAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@RoomsList, "Clicked $position", Toast.LENGTH_SHORT).show()
                selected_room= roomArrayList[position].room.toString()
                intent.putExtra("room", "$selected_room")
                //Log.d("Room", "$selected_room")
                startActivity(intent)
            }
        })
        getRoomData()
    }

    private fun getRoomData(){
        db= FirebaseFirestore.getInstance()
        db.collection("Rooms").
                addSnapshotListener(object: EventListener<QuerySnapshot>{
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error != null){
                            Log.e("Firestore Error", error.message.toString())
                            return
                        }
                        for(dc: DocumentChange in value?.documentChanges!!){
                            if(dc.type== DocumentChange.Type.ADDED){
                                roomArrayList.add(dc.document.toObject(Room::class.java))
                            }
                        }
                        myAdapter.notifyDataSetChanged()
                    }
                })
    }

}
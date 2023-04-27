package com.example.datasenreceive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datasenreceive.databinding.ActivityMain2Binding

import com.google.firebase.database.*


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<Users>
    private lateinit var database: DatabaseReference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView=findViewById(R.id.recyclerView)

        recyclerView.layoutManager=LinearLayoutManager(this)
        userArrayList= arrayListOf()
        database=FirebaseDatabase.getInstance().getReference("userData").child("a")

        database.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(dataSnapShot in snapshot.children)
                    {
                        val user=dataSnapShot.getValue(Users::class.java)
                        if(!userArrayList.contains(user))
                        {
                            userArrayList.add(user!!)
                        }
                    }
                    recyclerView.adapter=MyAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity2, error.toString(), Toast.LENGTH_SHORT).show()
            }

        })



    }
}

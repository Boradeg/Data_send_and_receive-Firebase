package com.example.datasenreceive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import com.example.datasenreceive.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.*
var empId : String? =null
class MainActivity : AppCompatActivity() {
    var nameUser : String? =null

    private lateinit var binding:ActivityMainBinding
    private lateinit var data: MyData
    private lateinit var database: DatabaseReference
    private lateinit var dbref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            writeData()

        }

           //
        binding.viewButton.setOnClickListener {

            //readData(empId)
            startActivity(Intent(this,MainActivity2::class.java))

        }

    }

    private fun writeData() {
        dbref=FirebaseDatabase.getInstance().getReference("userData").child("a")
        nameUser=binding.name.text.toString()
        val ageUser=binding.age.text.toString()
        empId=dbref.push().key!!
        val MyData=MyData(nameUser,ageUser)

        dbref.child(nameUser!!).setValue(MyData).addOnCompleteListener{
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun readData(empId: String?) {
         database=FirebaseDatabase.getInstance().getReference("userData")
            if (empId != null) {
                database.child(empId).child("userTrustyNumber").get().addOnSuccessListener {
                    binding.name1.text= it.child("name").value as CharSequence?
                }
            }
    }
}


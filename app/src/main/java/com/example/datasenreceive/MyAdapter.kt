package com.example.datasenreceive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val userList:ArrayList<Users>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
      val tvName:TextView=itemView.findViewById(R.id.tvName)
      val tvAge:TextView=itemView.findViewById(R.id.tvAge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val itemView=LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.tvName.text=userList[position].name
      holder.tvAge.text=userList[position].age
    }

}
package com.example.steven.stk

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.item_contact.view.*

/**
 * Created by steven on 20/3/2018.
 */
class MainAdapter(mContext : Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

     var mItemList : ArrayList<MainData> = ArrayList()

    init {
        Toast.makeText(mContext, "Yeah!!!", Toast.LENGTH_LONG).show()
    }


    constructor(context: Context, itemList: ArrayList<MainData>) : this(context){
        mItemList = itemList
        mItemList.add(MainData(name = "Guest", age = 50, address = "TW")).let { Log.d("StevenCheck", "added Guest") }
        mItemList.add(itemList[3].copy(name = "Plastic"))
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_contact, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder?, position: Int) {
       // holder?.itemView?.rootView?.name?.text = "position: ${position} name:${mItemList!![position].name}"
        holder?.bind(mainData = mItemList[position])
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(mainData : MainData){
            itemView.name.text = "NEW  name:${mainData.name} age:${mainData.age}"
        }
    }





}
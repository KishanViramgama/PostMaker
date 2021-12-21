package com.app.postmaker.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.postmaker.R
import java.io.File

class MyWorkAdapter(private var activity: Activity, private var fileList: MutableList<File>) :
    RecyclerView.Adapter<MyWorkAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWorkAdapter.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.my_work_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyWorkAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

}
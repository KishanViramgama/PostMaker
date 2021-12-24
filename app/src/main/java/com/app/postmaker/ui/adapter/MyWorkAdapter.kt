package com.app.postmaker.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.postmaker.R
import com.app.postmaker.ui.activity.ViewWork
import com.bumptech.glide.Glide
import java.io.File


class MyWorkAdapter(
    private var activity: Activity,
    private var fileList: MutableList<File>,
    private var columnWidth: Int
) :
    RecyclerView.Adapter<MyWorkAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.my_work_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imageView.layoutParams =
            ConstraintLayout.LayoutParams(columnWidth, columnWidth)

        Glide.with(activity).load(fileList[position].toString()).placeholder(R.mipmap.ic_launcher)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            activity.startActivity(
                Intent(activity, ViewWork::class.java)
                    .putExtra("path", fileList[position].toString())
            )
        }

    }

    override fun getItemCount(): Int {
        return fileList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView = itemView.findViewById(R.id.imageView_myWork_adapter)

    }

}
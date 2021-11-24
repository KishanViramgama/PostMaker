package com.app.postmaker.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.postmaker.R
import com.app.postmaker.interfaces.OnClick
import com.bumptech.glide.Glide

class ImageAdapter(
    private var activity: Activity,
    private var int: IntArray,
    private val onClick: OnClick
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(activity).inflate(R.layout.frame_adapter, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(activity).load(int[position]).into(holder.image)

        holder.image.setOnClickListener { onClick.click(position) }

    }

    override fun getItemCount(): Int {
        return int.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.imageView_frame_adapter);

    }

}
package com.app.postmaker.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.postmaker.R
import com.app.postmaker.database.DatabaseClient
import com.app.postmaker.item.Profile
import com.google.android.material.textview.MaterialTextView

class FrameAdapter(private var activity: Activity, private var frameDesign: IntArray) :
    RecyclerView.Adapter<FrameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val profile: Profile? = DatabaseClient.getInstance(activity)?.appDatabase
            ?.userTask()
            ?.getData()
        if (profile != null) {
            holder.textViewName.text = profile.name
            holder.textViewEmail.text = profile.email
            holder.textViewPhoneNo.text = profile.phoneNo
            holder.textViewWebSite.text = profile.webSite
        } else {
            holder.textViewName.text = activity.resources.getString(R.string.app_name)
            holder.textViewEmail.text = activity.resources.getString(R.string.app_name)
            holder.textViewPhoneNo.text = activity.resources.getString(R.string.app_name)
            holder.textViewWebSite.text = activity.resources.getString(R.string.app_name)
        }

    }

    override fun getItemCount(): Int {
        return frameDesign.size
    }

    override fun getItemViewType(position: Int): Int {
        return frameDesign[position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewName: MaterialTextView = itemView.findViewById(R.id.textView_name_frameA)
        var textViewEmail: MaterialTextView = itemView.findViewById(R.id.textView_email_frameA)
        var textViewPhoneNo: MaterialTextView = itemView.findViewById(R.id.textView_phoneNo_frameA)
        var textViewWebSite: MaterialTextView = itemView.findViewById(R.id.textView_webSite_frameA)


    }

}
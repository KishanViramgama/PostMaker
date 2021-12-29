package com.app.postmaker.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.app.postmaker.R
import com.app.postmaker.interfaces.OnClick
import com.app.postmaker.ui.adapter.FrameAdapter
import com.app.postmaker.ui.adapter.ImageAdapter
import com.bumptech.glide.Glide
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.net.Uri

import android.system.Os.link

import androidx.core.content.FileProvider
import com.app.postmaker.BuildConfig
import java.io.File


class HomeFragment : Fragment() {

    private lateinit var con: ConstraintLayout
    private lateinit var imageView: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewPager2: ViewPager2

    private lateinit var frameAdapter: FrameAdapter
    private var frameDesign = intArrayOf(R.layout.frame_a, R.layout.frame_b)
    private var image = intArrayOf(R.drawable.a, R.drawable.b)

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.home_fragment, container, false)

        val onClick = object : OnClick {
            override fun click(i: Int) {
                activity?.let { Glide.with(it).load(image[i]).into(imageView) }
            }
        }

        con = view.findViewById(R.id.con_frame)
        imageView = view.findViewById(R.id.imageView_main)
        recyclerView = view.findViewById(R.id.recyclerView)
        viewPager2 = view.findViewById(R.id.viewPager2)

        recyclerView.setHasFixedSize(true)
        val linearLayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager

        activity?.let { Glide.with(it).load(image[0]).into(imageView) }

        val imageAdapter = activity?.let { ImageAdapter(it, image, onClick) }
        recyclerView.adapter = imageAdapter

        frameAdapter = activity?.let { FrameAdapter(it, frameDesign) }!!
        viewPager2.adapter = frameAdapter

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        })

        setHasOptionsMenu(true)
        return view

    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @SuppressLint("WrongThread")
    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                ImageData("share")
            }
            R.id.save -> {
                ImageData("Save")
            }
        }
        return true
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun ImageData(type: String) {

        val path = if (type == "share") {
            requireActivity().externalCacheDir!!.absolutePath
        } else {
            requireActivity().getExternalFilesDir(resources.getString(R.string.app_name))
                .toString()
        }

        try {

            val date = Date()
            //Pattern for showing milliseconds in the time "SSS"
            @SuppressLint("SimpleDateFormat") val sdf =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
            val stringDate: String = sdf.format(date)

            val bitmap = Bitmap.createBitmap(
                con.width,
                con.height,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            con.draw(canvas)

            val outputStream = FileOutputStream("$path/$stringDate.jpg")
            val quality = 100
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.flush()
            outputStream.close()

            if (type == "share") {
                val contentUri: Uri =
                    FileProvider.getUriForFile(
                        requireActivity(),
                        BuildConfig.APPLICATION_ID + ".fileprovider",
                        File("$path/$stringDate.jpg")
                    )
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "image/*"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    requireActivity().resources.getString(R.string.app_name)
                )
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
                activity!!.startActivity(
                    Intent.createChooser(
                        shareIntent,
                        activity!!.resources.getString(R.string.share_to)
                    )
                )
            } else {
                Toast.makeText(activity, resources.getString(R.string.save), Toast.LENGTH_SHORT)
                    .show()
            }

        } catch (e: Throwable) {
            e.printStackTrace()  // Several error may come out with file handling or DOM
        }


    }

}


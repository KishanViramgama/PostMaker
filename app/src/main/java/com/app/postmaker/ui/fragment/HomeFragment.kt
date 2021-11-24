package com.app.postmaker.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.google.android.material.button.MaterialButton
import java.io.FileOutputStream


class HomeFragment : Fragment() {

    private lateinit var con: ConstraintLayout
    private lateinit var imageView: ImageView
    private lateinit var button: MaterialButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewPager2: ViewPager2

    private lateinit var frameAdapter: FrameAdapter
    private var frameDesign = intArrayOf(R.layout.frame_a, R.layout.frame_b)
    private var image = intArrayOf(R.drawable.a, R.drawable.b);

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View =
            inflater.inflate(R.layout.home_fragment, container, false)

        val onClick = object : OnClick {
            override fun click(i: Int) {
                activity?.let { Glide.with(it).load(image[i]).into(imageView) }
            }
        }

        con = view.findViewById(R.id.con_frame)
        button = view.findViewById(R.id.button_main)
        imageView = view.findViewById(R.id.imageView_main)
        recyclerView = view.findViewById(R.id.recyclerView)
        viewPager2 = view.findViewById(R.id.viewPager2)

        recyclerView.setHasFixedSize(true)
        val linearLayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager;

        activity?.let { Glide.with(it).load(image[0]).into(imageView) }

        val imageAdapter = activity?.let { ImageAdapter(it, image, onClick) }
        recyclerView.adapter = imageAdapter

        frameAdapter = activity?.let { FrameAdapter(it, frameDesign) }!!
        viewPager2.adapter = frameAdapter

        button.setOnClickListener {


            con.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(con.drawingCache)
            con.isDrawingCacheEnabled = false

            val mPath = requireActivity().externalCacheDir!!.absolutePath

            try {
                val outputStream = FileOutputStream(mPath + "_" + "a.jpg")
                val quality = 100
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                outputStream.flush()
                outputStream.close()
            } catch (e: Throwable) {
                // Several error may come out with file handling or DOM
                e.printStackTrace()
            }


        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Toast.makeText(activity, "page change", Toast.LENGTH_SHORT).show()
            }

        })

        return view

    }

}


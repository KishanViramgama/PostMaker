package com.app.postmaker.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.postmaker.R
import com.app.postmaker.interfaces.OnClick
import com.app.postmaker.ui.adapter.ImageAdapter
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton


class HomeFragment : Fragment() {

    var image = intArrayOf(R.drawable.a, R.drawable.b);

    private lateinit var recyclerView: RecyclerView;
    private lateinit var imageView: ImageView;
    private lateinit var button: MaterialButton;

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

        recyclerView = view.findViewById(R.id.recyclerView)
        imageView = view.findViewById(R.id.imageView_main)
        button = view.findViewById(R.id.button_main)

        recyclerView.setHasFixedSize(true)
        val linearLayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager;

        activity?.let { Glide.with(it).load(image[0]).into(imageView) }

        val imageAdapter = activity?.let { ImageAdapter(it, image, onClick) }
        recyclerView.adapter = imageAdapter

        button.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout_main, FrameFragment(), getString(R.string.home))
                .addToBackStack(getString(R.string.home))
                .commitAllowingStateLoss()

        }

        return view

    }

}
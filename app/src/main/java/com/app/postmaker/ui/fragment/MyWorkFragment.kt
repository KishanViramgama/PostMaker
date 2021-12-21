package com.app.postmaker.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.app.postmaker.R
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.io.File
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList


class MyWorkFragment : Fragment() {

    private val fileList: MutableList<File> = ArrayList()
    lateinit var progressBar: CircularProgressIndicator;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.my_work_fragment, container, false)

        progressBar = view.findViewById(R.id.progressBar_myWork_fragment)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {

            progressBar.show()

            /*
            * Your task will be executed here
            * you can execute anything here that
            * you cannot execute in UI thread
            * for example a network operation
            * This is a background thread and you cannot
            * access view elements here
            *
            * its like doInBackground()
            * */
            handler.post {
                /*
                * You can perform any operation that
                * requires UI Thread here.
                *
                * its like onPostExecute()
                * */

                try {
                    val files: Queue<File> =
                        LinkedList(listOf(File(requireActivity().externalCacheDir!!.absolutePath)))
                    while (!files.isEmpty()) {
                        val file: File = files.remove()
                        if (file.isDirectory) {
                            files.addAll(file.listFiles()!!)
                        } else if (file.name.endsWith(".jpg")) {
                            fileList.add(file)
                        }
                    }
                    progressBar.hide()
                    Log.d("data_list", fileList.size.toString())
                } catch (e: Exception) {
                    Log.d("error", e.toString())
                }

            }
        }

        return view
    }

}
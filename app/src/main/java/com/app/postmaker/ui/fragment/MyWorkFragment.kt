package com.app.postmaker.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.postmaker.R
import com.app.postmaker.eventbus.Events
import com.app.postmaker.eventbus.GlobalBus
import com.app.postmaker.eventbus.GlobalBus.bus
import com.app.postmaker.ui.adapter.MyWorkAdapter
import com.app.postmaker.util.Method
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textview.MaterialTextView
import org.greenrobot.eventbus.Subscribe
import java.io.File
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList


class MyWorkFragment : Fragment() {

    private lateinit var method: Method
    private lateinit var myWorkAdapter: MyWorkAdapter
    private val fileList: MutableList<File> = ArrayList()
    private lateinit var progressBar: CircularProgressIndicator;
    private lateinit var recyclerView: RecyclerView
    private lateinit var textViewNoData: MaterialTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(
            R.layout.my_work_fragment, container, false
        )

        bus!!.register(this);

        method = activity?.let { Method(it) }!!

        progressBar = view.findViewById(R.id.progressBar_myWork_fragment)
        recyclerView = view.findViewById(R.id.recyclerView_myWorkFragment)
        textViewNoData = view.findViewById(R.id.textView_myWork_fragment)

        textViewNoData.visibility = View.GONE

        recyclerView.setHasFixedSize(true)
        val linearLayoutManager =
            GridLayoutManager(activity, 2)
        recyclerView.layoutManager = linearLayoutManager;

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
                        LinkedList(
                            listOf(
                                File(
                                    requireActivity().getExternalFilesDir(
                                        resources.getString(
                                            R.string.app_name
                                        )
                                    ).toString()
                                )
                            )
                        )
                    while (!files.isEmpty()) {
                        val file: File = files.remove()
                        if (file.isDirectory) {
                            files.addAll(file.listFiles()!!)
                        } else if (file.name.endsWith(".jpg")) {
                            fileList.add(file)
                        }
                    }
                    progressBar.hide()
                    Log.d(
                        "data_list", fileList.size.toString()
                    )
                    if (fileList.size != 0) {
                        textViewNoData.visibility = View.GONE

                        myWorkAdapter = activity?.let {
                            MyWorkAdapter(
                                it,
                                fileList,
                                method.getScreenWidth() / 2
                            )
                        }!!
                        recyclerView.adapter = myWorkAdapter

                    } else {
                        textViewNoData.visibility = View.VISIBLE
                    }
                } catch (e: Exception) {
                    Log.d("error", e.toString())
                }

            }
        }

        return view
    }


    @Subscribe
    fun getData(notify: Events.Notify) {
        fileList.removeAt(notify.getPosition())
        myWorkAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Unregister the registered event.
        bus!!.unregister(this)
    }

}
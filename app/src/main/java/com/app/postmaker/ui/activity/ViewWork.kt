package com.app.postmaker.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.app.postmaker.BuildConfig
import com.app.postmaker.R
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import java.io.File


class ViewWork : AppCompatActivity() {

    private lateinit var string: String
    private lateinit var toolbar: MaterialToolbar
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_work)

        string = intent.getStringExtra("path")!!

        toolbar = findViewById(R.id.toolbar_viewWork)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        imageView = findViewById(R.id.imageView_viewWork)

        Glide.with(this).load(string).placeholder(R.mipmap.ic_launcher).into(imageView)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.work, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                val contentUri: Uri =
                    FileProvider.getUriForFile(
                        this,
                        BuildConfig.APPLICATION_ID + ".fileprovider",
                        File(string)
                    )
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "image/*"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    resources.getString(R.string.app_name)
                )
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
                startActivity(
                    Intent.createChooser(
                        shareIntent,
                        resources.getString(R.string.share_to)
                    )
                )
            }
            R.id.delete -> {
                File(string).delete()
                onBackPressed()
            }
            android.R.id.home -> onBackPressed()
            else -> {}
        }
        return true
    }

}
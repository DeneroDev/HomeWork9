package com.example.denero.homework9

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Base64
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.File


class MainActivity : AppCompatActivity() {
    lateinit var data:ArrayList<File>
    lateinit var recView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recView = rec_main

        var permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        var a = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this, a,
                    1)
        }



        data = DataNotify.instance.dataNotify(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM))

        recView.layoutManager = GridLayoutManager(applicationContext,4)
        recView.adapter = GalleryAdapter(data)


    }
}

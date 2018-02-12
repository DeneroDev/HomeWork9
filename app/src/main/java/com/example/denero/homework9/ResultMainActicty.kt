package com.example.denero.homework9

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.result_main.*
import android.graphics.Bitmap.CompressFormat
import android.R.attr.bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.Toast
import java.io.FileOutputStream


/**
 * Created by Denero on 12.02.2018.
 */
class ResultMainActicty:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_main)

        var imgVResult:ImageView = imgVResult
        var imgVResource:ImageView = imgVResource

        var bitmap:Bitmap = BitmapFactory.decodeFile(intent.extras.getString("pathResource"))
        imgVResource.setImageBitmap(bitmap)

        Picasso.with(applicationContext)
                .load(intent.extras.getString("pathResult"))
                .placeholder(R.drawable.abc_ic_arrow_drop_right_black_24dp)
                .into(imgVResult)

        var btnSave:Button = save_pic_btn
        btnSave.setOnClickListener {
            val fos = FileOutputStream(intent.extras.getString("pathResource") + "PR.jpg")
            var bit = (imgVResult.drawable as BitmapDrawable).bitmap
            bit.compress(CompressFormat.JPEG, 75, fos)
            fos.flush()
            fos.close()
            Toast.makeText(applicationContext,"Saved!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
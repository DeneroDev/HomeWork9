package com.example.denero.homework9

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.renderscript.Element
import android.support.v4.content.FileProvider
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.util.Log
import android.view.ViewGroup
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.*
import java.net.URL
import java.nio.file.Files
import javax.xml.datatype.DatatypeFactory


class GalleryAdapter(var data:ArrayList<File>): RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    override fun onBindViewHolder(holder: GalleryViewHolder?, position: Int) {
        var bitmap = BitmapFactory.decodeFile(data.get(position).absolutePath)
        holder!!.img.setImageBitmap(bitmap)
        holder.v.setOnClickListener {
            try {
                holder?.img?.setOnLongClickListener {
                    val popup = PopupMenu(holder.v.getContext(), holder.v)
                    popup.inflate(R.menu.popup_menu)
                    popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
                        override fun onMenuItemClick(p0: MenuItem?): Boolean {

                            when(p0?.order) {
                                100 ->{
                                    var file = RequestBody.create(MediaType.parse("image/*"),data.get(position))
                                    var a = MultipartBody.Part.createFormData("image",data.get(position).name,file)
                                    ApiHelper.instance.ColorImage(a,holder.v.context,data.get(position))
                                }
                                99 ->{
                                    var fileA = RequestBody.create(MediaType.parse("image/*"),data.get(position))
                                    var fileS = RequestBody.create(MediaType.parse("image/*"),data.get(position+2))
                                    var a = MultipartBody.Part.createFormData("content",data.get(position).name,fileA)
                                    var s = MultipartBody.Part.createFormData("style",data.get(position+2).name,fileS)
                                    ApiHelper.instance.NeutralImage(a,s,holder.v.context,data.get(position))
                                }
                                else->{
                                    Toast.makeText(holder.v.context, p0?.itemId.toString(), Toast.LENGTH_LONG).show()
                                }
                            }
                            return true
                        }
                    })
                    popup.show()
                    true
                }
            }catch (e:Exception){
                Toast.makeText(holder?.v?.context,"Ошибка", Toast.LENGTH_LONG).show()
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GalleryViewHolder = GalleryViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.item,parent,false))

    override fun getItemCount(): Int = data.size

    class GalleryViewHolder(var v:View):RecyclerView.ViewHolder(v) {
        var img:ImageView = v.findViewById(R.id.img_photo)
    }

    fun BitMapToString(bitmap: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos)
        val b = baos.toByteArray()
        return b
    }


}


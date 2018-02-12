package com.example.denero.homework9

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Denero on 12.02.2018.
 */
class ApiHelper private constructor() {
    open lateinit var instance:ApiHelper
    init {
        instance = this
    }
    private object Holder{val INSTANCE = ApiHelper()}

    companion object {
        val instance: ApiHelper by lazy { ApiHelper.Holder.INSTANCE }
    }

    public fun ColorImage(image:MultipartBody.Part,context: Context,res:File){

        var client = OkHttpClient().newBuilder()
                .readTimeout(600,TimeUnit.SECONDS)
                .writeTimeout(600,TimeUnit.SECONDS)
                .build()



        var retrofit = Retrofit.Builder()
                .baseUrl("https://api.deepai.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        var service = retrofit.create(APIService::class.java)

        var result = service.imageColor(image)

        result.enqueue(object: Callback<ResponceImage> {
            override fun onFailure(call: Call<ResponceImage>?, t: Throwable?) {
                Log.d("SHO ZA&!",t.toString())
            }

            override fun onResponse(call: Call<ResponceImage>?, response: Response<ResponceImage>?) {
                var data:ResponceImage ?= response!!.body()
                Log.d("TAK::",response!!.body()!!.outputUrl)
                var intent = Intent(context,ResultMainActicty::class.java)
                intent.putExtra("pathResult",response!!.body()!!.outputUrl)
                intent.putExtra("pathResource",res.absolutePath)
                context.startActivity(intent)
            }

        })

    }
    public fun NeutralImage(image:MultipartBody.Part,style:MultipartBody.Part,context: Context,res:File){

        var client = OkHttpClient().newBuilder()
                .readTimeout(600,TimeUnit.SECONDS)
                .writeTimeout(600,TimeUnit.SECONDS)
                .build()



        var retrofit = Retrofit.Builder()
                .baseUrl("https://api.deepai.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        var service = retrofit.create(APIService::class.java)

        var result = service.imageStyle(image,style)

        result.enqueue(object: Callback<ResponceImage> {
            override fun onFailure(call: Call<ResponceImage>?, t: Throwable?) {
                Log.d("SHO ZA&!",t.toString())
            }

            override fun onResponse(call: Call<ResponceImage>?, response: Response<ResponceImage>?) {
                var data:ResponceImage ?= response!!.body()
                try{
                    Log.d("TAK::",response!!.body()!!.outputUrl)
                    var intent = Intent(context,ResultMainActicty::class.java)
                    intent.putExtra("pathResult",response!!.body()!!.outputUrl)
                    intent.putExtra("pathResource",res.absolutePath)
                    context.startActivity(intent)
                }catch (e:Exception){
                    Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show()
                }

            }

        })

    }
}
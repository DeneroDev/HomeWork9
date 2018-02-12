package com.example.denero.homework9

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

/**
 * Created by Denero on 12.02.2018.
 */
interface APIService {
    @Multipart
    @Headers("Api-Key:9e9563a6-5021-48dc-9c59-e1c91b3c47cf")
    @POST("api/colorizer")
    fun imageColor(@Part image: MultipartBody.Part): Call<ResponceImage>


    @Multipart
    @Headers("Api-Key:9e9563a6-5021-48dc-9c59-e1c91b3c47cf")
    @POST("api/neural-style")
    fun imageStyle(@Part image: MultipartBody.Part, @Part style:MultipartBody.Part): Call<ResponceImage>
}
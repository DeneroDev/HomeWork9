package com.example.denero.homework9

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Denero on 12.02.2018.
 */
class ResponceImage {
    @SerializedName("job_id")
    @Expose
    public var jobId: Int? = null
    @SerializedName("output_url")
    @Expose
    public var outputUrl: String? = null
}
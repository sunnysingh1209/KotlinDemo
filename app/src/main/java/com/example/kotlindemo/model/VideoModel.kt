package com.example.kotlindemo.model

import java.io.Serializable

data class VideoModel(
    var videoId: String, var publishedAt: String = "",
    var channelId: String = "",
    var title: String = "",
    var description: String = "",
    var urlMedium: String = ""
) : Serializable {


}
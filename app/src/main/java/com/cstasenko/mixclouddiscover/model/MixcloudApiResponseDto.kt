package com.cstasenko.mixclouddiscover.model

import com.google.gson.annotations.SerializedName

data class MixcloudApiResponseDto(
    val data: List<Metadata>,
    val paging: Paging
)

data class Metadata(
    val key: String,
    val url: String,
    val name: String,
    val tags: List<Tag>,
    @SerializedName("created_time")
    val createdTime: String,
    @SerializedName("updated_time")
    val updatedTime: String,
    @SerializedName("play_count")
    val playCount: Int,
    @SerializedName("favorite_count")
    val favoriteCount: Int,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("listener_count")
    val listenerCount: Int,
    @SerializedName("repost_count")
    val repostCount: Int,
    val pictures: Pictures,
    val slug: String,
    val user: User,
    @SerializedName("hidden_stats")
    val hiddenStats: Boolean,
    @SerializedName("audio_length")
    val audioLength: Int
)

data class Paging(
    val next: String
)

data class Pictures(
    val small: String,
    val thumbnail: String,
    @SerializedName("medium_mobile")
    val mediumMobile: String,
    val medium: String,
    val large: String,
    @SerializedName("320wx320h")
    val resolution320: String,
    @SerializedName("extra_large")
    val extraLarge: String,
    @SerializedName("640wx640h")
    val resolution640: String,
    @SerializedName("768wx768h")
    val resolution768: String?,
    @SerializedName("1024wx1024h")
    val resolution1024: String?
)

data class Tag(
    val key: String,
    val url: String,
    val name: String
)

data class User(
    val key: String,
    val url: String,
    val name: String,
    val username: String,
    val pictures: Pictures
)

//data class PicturesX(
//    val `320wx320h`: String,
//    val `640wx640h`: String,
//    val extra_large: String,
//    val large: String,
//    val medium: String,
//    val medium_mobile: String,
//    val small: String,
//    val thumbnail: String
//)
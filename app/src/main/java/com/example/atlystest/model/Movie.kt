package com.example.atlystest.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity
data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
)

package com.example.atlystest.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("results")
    val results: List<Movie>
)

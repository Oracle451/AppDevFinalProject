package com.example.finalproject.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val overview: String,
    val release_date: String,
    val vote_average: Float
) : Parcelable


package com.cellcom.cellcompopulars.populars



//@Entity(primaryKeys = ["id"])
data class Popular(
    val adult: Boolean,
    val backdrop_path: String? = "",
    val genre_ids: ArrayList<Int>,
    val id: Int,
    val original_language: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    var isFavorite: Boolean = false
 ) {


 }

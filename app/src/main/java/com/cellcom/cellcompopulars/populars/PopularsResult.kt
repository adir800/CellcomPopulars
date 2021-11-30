package com.cellcom.cellcompopulars.populars



//@Entity(primaryKeys = ["page"])
data class PopularsResult(
    val page: Int,
    val results: ArrayList<Popular>,
    val total_pages: Int,
    val total_results: Int

    ) {


 }

package com.cellcom.cellcompopulars.populars

import java.util.*
import kotlin.collections.ArrayList

class PopularFilter {
    companion object {

        fun newestFilter(populars: ArrayList<Popular>): List<Popular> {
            var newest = populars.filter {
                var date: Date = Date(it.release_date)
                var dateThreshold = Date("2021-10-01")
                (date > dateThreshold)
            }
            return newest
        }

        fun favoritesFilter(populars: ArrayList<Popular>): List<Popular> {
            var favorite = populars.filter {
                (it.isFavorite)
            }
            return favorite
        }
    }
}
package com.cellcom.cellcompopulars.populars

import android.content.Context
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.cellcom.cellcompopulars.MainActivity
import com.cellcom.cellcompopulars.Prefs
import com.cellcom.cellcompopulars.R
import com.cellcom.cellcompopulars.api.APIClient
import com.cellcom.cellcompopulars.populars.recycleview.PopularsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularsViewModel : ViewModel() {
    var pagesSize: Int? = null


     fun getPopulars(context: Context,recyclerview: RecyclerView, page: Int = 1, isScrollingUp: Boolean = false) {
        var api = APIClient.instance?.getMyApi()

         System.out.println("page = $page")
         System.out.println("pagesSize = ${Prefs.pagesSize}")

         if(page < 1 || (Prefs.pagesSize != null && page > Prefs.pagesSize!!)) {
             return
         }

        api?.getPopularMoviesByPage(page)?.enqueue(object : Callback<PopularsResult> {
            override fun onResponse(call: Call<PopularsResult>, response: Response<PopularsResult>) {
                Log.i(MainActivity::class.simpleName, "on Success!!!!")
                Log.i(MainActivity::class.simpleName, "response = ${response.body()}")
                Prefs.pagesSize = response.body()?.total_pages
                var populars = response.body()?.results

        val adapter = populars?.let { PopularsAdapter(it) }

        recyclerview.adapter = adapter
                if (adapter != null) {
                    adapter.onItemClick = { popular ->

                        // do something with your item
                        Log.d("Popular: Item Clicked", popular.title)

                        val dialog = MaterialDialog(context)
                            .customView(R.layout.chosen_popular, scrollable = true)

                        val customView = dialog.getCustomView()
                        var title: TextView = customView.findViewById(R.id.title)
                        var summary: TextView = customView.findViewById(R.id.summary)
                        var fav: CheckBox = customView.findViewById(R.id.checkBox)
                        title.text = popular.title
                        summary.text = popular.overview
                        fav.isChecked = popular.isFavorite
                        fav.setOnCheckedChangeListener { buttonView, isChecked ->
                                popular.isFavorite = isChecked
                        }
                        dialog.show()

//                        context?.let {
//                            MaterialDialog(it).show {
//                                customView(R.layout.chosen_popular)
////                                title(text = popular.title)
////                                message(text = popular.overview)
//                            }
//                        }
                    }
                }

            }

            override fun onFailure(call: Call<PopularsResult>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
            }

        })

    }

    fun getInTheater(context: Context,recyclerview: RecyclerView, page: Int = 1, isScrollingUp: Boolean = false) {
        var api = APIClient.instance?.getMyApi()

        System.out.println("page = $page")
        System.out.println("pagesSize = ${Prefs.pagesSize}")

        if(page < 1 || (Prefs.pagesSize != null && page > Prefs.pagesSize!!)) {
            return
        }

        api?.getInTheaterMoviesByPage(page)?.enqueue(object : Callback<PopularsResult> {
            override fun onResponse(call: Call<PopularsResult>, response: Response<PopularsResult>) {
                Log.i(MainActivity::class.simpleName, "on Success!!!!")
                Log.i(MainActivity::class.simpleName, "response = ${response.body()}")
                Prefs.pagesSize = response.body()?.total_pages
                var newest = response.body()?.results

                val adapter = newest?.let { PopularsAdapter(it) }

                recyclerview.adapter = adapter
                if (adapter != null) {
                    adapter.onItemClick = { newest ->

                        // do something with your item
                        Log.d("Newest: Item Clicked", newest.title)

                        val dialog = MaterialDialog(context)
                            .customView(R.layout.chosen_newest, scrollable = true)

                        val customView = dialog.getCustomView()
                        var title: TextView = customView.findViewById(R.id.title)
                        var summary: TextView = customView.findViewById(R.id.summary)
                        var fav: CheckBox = customView.findViewById(R.id.checkBox)
                        title.text = newest.title
                        summary.text = newest.overview
                        fav.isChecked = newest.isFavorite
                        fav.setOnCheckedChangeListener { buttonView, isChecked ->
                            newest.isFavorite = isChecked
                        }
                        dialog.show()

                    }
                }

            }

            override fun onFailure(call: Call<PopularsResult>, t: Throwable) {
                Log.i(MainActivity::class.simpleName, "on FAILURE!!!!")
            }

        })
    }
}
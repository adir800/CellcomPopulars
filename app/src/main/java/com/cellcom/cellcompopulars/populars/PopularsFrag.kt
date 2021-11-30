package com.cellcom.cellcompopulars.populars

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.cellcom.cellcompopulars.OnSwipeTouchListener
import com.cellcom.cellcompopulars.Prefs
import com.cellcom.cellcompopulars.R
import com.cellcom.cellcompopulars.populars.recycleview.PopularsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class PopularsFrag : Fragment() {
    var recyclerview: RecyclerView? = null
    var layoutManager: LinearLayoutManager? = null
    var isLastPage: Boolean = false
    var isFirstPage: Boolean = false
    var isLoading: Boolean = false
    var popularsViewModel: PopularsViewModel? = null

    var page:Int = 1
    var listType: ListType = ListType.Populars

    companion object {
        fun newInstance() = PopularsFrag()
    }

    private lateinit var viewModel: PopularsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.populars_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PopularsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val myItems = listOf("Populars", "Newest", "Favorites")

            context?.let { it1 ->
                MaterialDialog(it1).show {
                    listItemsSingleChoice(items = myItems) { dialog, index, text ->
                        // Invoked when the user selects an item
                        when(index) {
                            0 -> {
                                listType = ListType.Populars
                                page = 1
                                context?.let { popularsViewModel!!.getPopulars(it, recyclerview!!) }
                            }
                            1 -> {
                                listType = ListType.Newest
                                page = 1
                                context?.let { popularsViewModel!!.getInTheater(it,recyclerview!!) }
                            }
//                            2 -> {
//                                val adapter = recyclerview?.adapter as PopularsAdapter
//                                adapter.updateList(PopularFilter.favoritesFilter(adapter.getList()) as ArrayList<Popular>)
//                            }
                        }
                    }
                    positiveButton(text = "Ok") { dialog ->
                        // Do something

                    }
                    negativeButton(text = "Cancel") { dialog ->
                        // Do something
                    }
                }
            }
        }

        val recyclerview = view.findViewById<RecyclerView>(R.id.recycler_view)

        layoutManager = LinearLayoutManager(view.context)
        recyclerview.layoutManager = layoutManager


        popularsViewModel = PopularsViewModel()
        context?.let { popularsViewModel!!.getPopulars(it,recyclerview) }

        recyclerview.setOnTouchListener(object : OnSwipeTouchListener(context) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                getMoreItems(recyclerview)
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                getLessItems(recyclerview)
            }
        })
        this.recyclerview = recyclerview

    }

    fun getMoreItems(recyclerview: RecyclerView) {
        //after fetching your data assuming you have fetched list in your
        // recyclerview adapter assuming your recyclerview adapter is
        //rvAdapter
//        after getting your data you have to assign false to isLoading
        isLoading = false
        if(page < Prefs.pagesSize!!) {
            page++
            when(listType) {
                ListType.Populars -> context?.let { popularsViewModel?.getPopulars(it,recyclerview, page) }
                ListType.Newest -> context?.let { popularsViewModel?.getInTheater(it,recyclerview, page) }
            }

        }

    }

    fun getLessItems(recyclerview: RecyclerView) {
        isLoading = false
        if(page > 1) {
            page--
            when(listType) {
                ListType.Populars -> context?.let { popularsViewModel?.getPopulars(it, recyclerview, page, true) }
                ListType.Newest -> context?.let { popularsViewModel?.getPopulars(it, recyclerview, page, true) }
            }
        }
    }
}

enum class ListType {
    Populars, Newest, Favorites
}
package com.cellcom.cellcompopulars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cellcom.cellcompopulars.populars.PopularsFrag
import com.cellcom.cellcompopulars.populars.recycleview.PopularsAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
//
//        recyclerview.layoutManager = LinearLayoutManager(this)
//
//        val data = ArrayList<String>()
//
//        for (i in 1..20) {
//            data.add("Item " + i)
//        }
//
//        val adapter = PopularsAdapter(data)
//        recyclerview.adapter = adapter





        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, PopularsFrag.newInstance(), "popularList")
                .commit()
        }
//

    }
}
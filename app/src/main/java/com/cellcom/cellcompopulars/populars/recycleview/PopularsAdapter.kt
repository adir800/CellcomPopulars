package com.cellcom.cellcompopulars.populars.recycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cellcom.cellcompopulars.R
import com.cellcom.cellcompopulars.populars.Popular


class PopularsAdapter (private val dataSet: ArrayList<Popular>) :
    RecyclerView.Adapter<PopularsAdapter.ViewHolder>() {

    var context: Context? = null
    var onItemClick: ((Popular) -> Unit)? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val popular = dataSet[position]

            // sets the image to the imageview from our itemHolder class
//        holder.imageView.setImageResource(ItemsViewModel.image)

            // sets the text to the textview from our itemHolder class
//        holder.textView.text = ItemsViewModel.text

            var fullUrl = "https://image.tmdb.org/t/p/w500" + popular.poster_path
//            val url = URL(fullUrl)
//            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

        context?.let {
            Glide.with(it)
                .load(fullUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView)
        };

        holder.textView.text = popular.title
//            holder.imageView.setImageBitmap(bmp)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun addData(listItems: ArrayList<Popular>) {
        var size = this.dataSet.size
        this.dataSet.addAll(listItems)
        var sizeNew = this.dataSet.size
        notifyItemRangeChanged(size, sizeNew)
    }

    fun updateList(listItems: ArrayList<Popular>) {
        var size = this.dataSet.size
        this.dataSet.clear()
        this.dataSet.addAll(listItems)
        var sizeNew = this.dataSet.size
        notifyItemRangeChanged(size, sizeNew)
    }

    fun getList(): ArrayList<Popular> {
        return this.dataSet
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(dataSet[adapterPosition])
            }
        }

        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        //        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}
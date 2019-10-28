package com.example.themoviedb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.MovieListClass.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class RecyclerViewAdapterClass(private val resultList: List<Result>) :
    RecyclerView.Adapter<RecyclerViewAdapterClass.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return RecyclerViewHolder(v)
    }

    override fun getItemCount() = resultList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(searchResult: Result) {
            val posterPath = searchResult.posterPath
            val posterBanner = itemView.imgSearchResultPoster
            Picasso.get().load("https://image.tmdb.org/t/p/w500/ $posterPath").into(posterBanner);
            itemView.txtMovieTitle.text = searchResult.title.toString()
            itemView.txtMovieOverView.text = searchResult.overview.toString() + "..."
//            itemView.item.setOnClickListener { activityFunction }
        }
    }
}
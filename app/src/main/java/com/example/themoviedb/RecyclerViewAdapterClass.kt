package com.example.themoviedb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.MovieListClass.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class RecyclerViewAdapterClass(private val resultList: List<Result>) : RecyclerView.Adapter<RecyclerViewAdapterClass.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false))
    }

    override fun getItemCount() = resultList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(
            resultList[position].title,
            resultList[position].overview,
            resultList[position].posterPath
        )
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieTitle: String, movieOverView: String, moviePosterPath: String) {
            val posterBaseUrl = "https://image.tmdb.org/t/p/w500"
            Picasso.get().load(posterBaseUrl + moviePosterPath).into(itemView.imgSearchResultPoster)
            itemView.txtMovieTitle.text = movieTitle
            itemView.txtMovieOverView.text = movieOverView
//            itemView.setOnClickListener { activityFunction(id) }
        }
    }
}
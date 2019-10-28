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
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return RecyclerViewHolder(v)
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

//            val moviePoster = movieOnlineList.get(position).getPoster()
//            Picasso.get().load(moviePoster).into(holder.imageViewPoster)
//            String moviePoster = movieOnlineList.get(position).getPoster();
            val posterBaseUrl = "https://image.tmdb.org/t/p/w500"
            Picasso.get().load(posterBaseUrl + moviePosterPath).into(itemView.imgSearchResultPoster)
//            Picasso.get().load("https://image.tmdb.org/t/p/w500 $moviePosterPath")
//                .into(itemView.imgSearchResultPoster)
            itemView.txtMovieTitle.text = movieTitle
            itemView.txtMovieOverView.text = movieOverView

//            val posterPath = moviePosterPath
//            val posterBanner = itemView.imgSearchResultPoster
//            Picasso.get().load("https://image.tmdb.org/t/p/w500/ $posterPath").into(posterBanner);
//            itemView.txtMovieTitle.text = movieTitle
//            itemView.txtMovieOverView.text = movieOverView
//            itemView.item.setOnClickListener { activityFunction }
        }
    }
}
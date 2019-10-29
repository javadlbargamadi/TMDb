package com.example.themoviedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.MovieListClass.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_search_result.*

class MovieSearchResultActivity : AppCompatActivity() {

    private val resultList = ArrayList<Result>()
    lateinit var adapter: RecyclerViewAdapterClass
    private val disposable = CompositeDisposable()
    //    private val bundle: Bundle? = intent.extras
//    private val userMovie = bundle?.get("userMovie")
    var searchResult = ArrayList<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search_result)

        val bundle: Bundle? = intent.extras
        val userMovie = bundle?.get("userMovie")

        setUpRecyclerView()
//        subscribeTo()

        disposable.add(
            RetrofitProviderClass.provideRetrofit().getMovieList(userMovie.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    searchResult = it.results as ArrayList<Result>
                    resultList.addAll(searchResult)
                    adapter.notifyDataSetChanged()
                }, {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }, {
                    Toast.makeText(this, "movieName call : successful", Toast.LENGTH_LONG).show()
                })
        )
    }

//    private fun showMovieDetail(it: String) {
//        disposable.add(
//            RetrofitProviderClass.provideRetrofit().getMovieDetail(it)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    btnSearch.setOnClickListener {
//                        intent = Intent(this, MovieDetailActivity::class.java)
//                        intent.putExtra("movieDetail", it.toString())
//                        startActivity(intent)
//                    }
//                }, {
//                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                }, {
//                    Toast.makeText(this, "movieId call : successful", Toast.LENGTH_LONG).show()
//                })
//        )
//    }

//    private fun subscribeTo() {
//        disposable.add(
//            RetrofitProviderClass.provideRetrofit().getMovieList(userMovie.toString())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    searchResult = it.results as ArrayList<Result>
//                    resultList.addAll(searchResult)
//                    adapter.notifyDataSetChanged()
//                }, {
//                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                }, {
//                    Toast.makeText(this, "movieName call : successful", Toast.LENGTH_LONG).show()
//                })
//        )
//    }

    private fun setUpRecyclerView() {
        adapter = RecyclerViewAdapterClass(resultList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
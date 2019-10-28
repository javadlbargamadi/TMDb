package com.example.themoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.MovieListClass.MovieListClass
import com.example.themoviedb.MovieListClass.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_search_result.*

class MovieSearchResultActivity : AppCompatActivity() {

    //    lateinit var resultList : List<Result>
    val resultList = ArrayList<Result>()
    lateinit var adapter: RecyclerViewAdapterClass
    private val disposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search_result)

        val bundle: Bundle? = intent.extras
        val userMovie = bundle?.get("userMovie")

        setUpRecyclerView()

//        recyclerView.adapter
//        recyclerView.layoutManager
//
//
//        var resultList : List<Result>
//
        var searchResult = ArrayList<Result>()
//
////        var resultList = MovieListClass.Re
//
//
        disposable.add(
            RetrofitProviderClass.provideRetrofit().getMovieList(userMovie.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    searchResult = it.results as ArrayList<Result>
                    resultList.addAll(searchResult)
                    adapter.notifyDataSetChanged()
//                resultList = it.results as ArrayList<Result>
//                recyclerView.adapter = RecyclerViewAdapterClass(it.results)
//                recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                }, {
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                }, {
                    Toast.makeText(this,"api call : successful",Toast.LENGTH_LONG).show()
                })
        )
//
//        recyclerView.adapter = RecyclerViewAdapterClass(resultList)
//        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


    }

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

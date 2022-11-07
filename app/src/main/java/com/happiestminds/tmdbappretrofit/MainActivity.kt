package com.happiestminds.tmdbappretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rView : RecyclerView
    lateinit var pBar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rView = findViewById(R.id.rView)
        pBar = findViewById(R.id.progressBar)
        rView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


        val key = resources.getString(R.string.api_key)
        Log.d("MainActivity","api_key : $key")

        val request = TMDBInterface.createInstance().getPopularMovies(key)
        request.enqueue(ResponseCallBack())
    }

    inner class ResponseCallBack:Callback<PopularMovies>{
        override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
            Log.d("MainActivity","onResponse Executed")
            pBar.visibility = View.INVISIBLE
            if(response.isSuccessful){
                response.body()?.let {
                    Log.d("MainActivity","Movies Count: ${it.results.size}")
                    rView.adapter = MovieAdapter(it.results){
                        val intent = Intent(this@MainActivity,ClickActivity::class.java)
                        intent.putExtra("movie",it)
                        startActivity(intent)
                    }
                }
            }else {
                Toast.makeText(this@MainActivity,"There seems to be some problem at the backend, try again later",Toast.LENGTH_LONG).show()
            }
        }

        //unsuccessful in accessing server or communicating
        override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
            Toast.makeText(this@MainActivity,"Oops!! Couldn't reach TMDB server, try again later",Toast.LENGTH_LONG).show()
            pBar.visibility = View.INVISIBLE
            Log.d("MainActivity","Failure msg: ${t.localizedMessage}")
        }

    }
}
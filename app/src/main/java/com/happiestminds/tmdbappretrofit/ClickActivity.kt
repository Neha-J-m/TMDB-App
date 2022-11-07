package com.happiestminds.tmdbappretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ClickActivity : AppCompatActivity() {
    lateinit var posterImageView: ImageView
    lateinit var overviewText : TextView
    lateinit var releaseDateTextView : TextView
    lateinit var ratingTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click)

        posterImageView = findViewById(R.id.posterImg)
        overviewText = findViewById(R.id.overviewText)
        releaseDateTextView = findViewById(R.id.releaseDT)
        ratingTextView = findViewById(R.id.ratingTE)

        val selectedMovie = intent.getParcelableExtra<Movie>("movie")
        val imgPath = "https://image.tmdb.org/t/p/original${selectedMovie?.poster_path}"
        Glide.with(this)
            .load(imgPath)
            .into(posterImageView)
        overviewText.text = selectedMovie?.overview
        releaseDateTextView.text = selectedMovie?.release_date
        ratingTextView.text = "${selectedMovie?.vote_average}"

    }
}
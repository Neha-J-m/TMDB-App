package com.happiestminds.tmdbappretrofit

import android.content.Intent
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(val data:List<Movie>,val action:(Movie)->Unit): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val imgV = itemView.findViewById<ImageView>(R.id.imageView)
        var titleTextView = itemView.findViewById<TextView>(R.id.titleT)
        val overviewTextView = itemView.findViewById<TextView>(R.id.overviewT)
        val releaseTextView = itemView.findViewById<TextView>(R.id.rDateT)
        val ratingTextView = itemView.findViewById<TextView>(R.id.ratingT)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        //inflate xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        //binds the data to the view
        val movie = data[position]
        holder.titleTextView.text = movie.title
        holder.overviewTextView.text = movie.overview
        holder.overviewTextView.movementMethod = ScrollingMovementMethod()
        holder.releaseTextView.text = movie.release_date
        holder.ratingTextView.text = "${movie.vote_average}"

        val imgPath = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
        Glide.with(holder.itemView)
            .load(imgPath)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(holder.imgV)

        holder.itemView.setOnClickListener{
            action(movie)

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
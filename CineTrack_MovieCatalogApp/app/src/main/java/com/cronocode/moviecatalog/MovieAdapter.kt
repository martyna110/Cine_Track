
package com.cronocode.moviecatalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cronocode.moviecatalog.models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    private var movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val likedMovies = mutableSetOf<Movie>()

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie, isLiked: Boolean, onLikeChanged: (Movie, Boolean) -> Unit) {
            itemView.movie_title.text = movie.title
            itemView.movie_release_date.text = movie.release
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_poster)

            val likeButton = itemView.findViewById<ImageView>(R.id.like_button)
            likeButton.setImageResource(
                if (isLiked) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off
            )

            likeButton.setOnClickListener {
                val newState = !isLiked
                likeButton.setImageResource(
                    if (newState) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off
                )
                onLikeChanged(movie, newState)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        val isLiked = likedMovies.contains(movie)
        holder.bindMovie(movie, isLiked) { m, liked ->
            if (liked) likedMovies.add(m) else likedMovies.remove(m)
        }
    }

    fun getLikedMovies(): List<Movie> = likedMovies.toList()

    fun updateData(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}

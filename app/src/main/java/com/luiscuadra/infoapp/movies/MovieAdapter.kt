package com.luiscuadra.infoapp.movies
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luiscuadra.infoApp.databinding.ItemMoviesBinding

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var movieList: List<TmdbMovie> = emptyList()
    private var itemClickListener:((TmdbMovie)->Unit)?=null

    fun updateList(newList: List<TmdbMovie>) {
        movieList = newList
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: (TmdbMovie) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviesBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
       val movie = movieList[position]
        holder.bind(movie)

        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(movie)
        }
    }

    override fun getItemCount(): Int = movieList.size
}
package com.luiscuadra.infoapp.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luiscuadra.infoApp.databinding.ItemTvshowsBinding

class TvShowAdapter : RecyclerView.Adapter<TvShowViewHolder>() {

    private var tvShowList: List<TmdbTvShow> = emptyList()
    private var itemClickListener: ((TmdbTvShow) -> Unit)? = null

    fun updateList(newList: List<TmdbTvShow>) {
        tvShowList = newList
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: (TmdbTvShow) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTvshowsBinding.inflate(inflater, parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShowList[position]
        holder.bind(tvShow)

        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(tvShow)
        }
    }

    override fun getItemCount(): Int = tvShowList.size
}
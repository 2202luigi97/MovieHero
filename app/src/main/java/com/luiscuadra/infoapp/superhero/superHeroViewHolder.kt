package com.luiscuadra.infoapp.superhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.luiscuadra.infoApp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class superHeroViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItem: superHeroItem, onItemSelected:(String) -> Unit){
        binding.tvSuperHeroName.text = superHeroItem.superheroName
        Picasso.get().load(superHeroItem.superHeroImage.url).into(binding.imgSuperHero)
        binding.root.setOnClickListener { onItemSelected(superHeroItem.superheroId) }

    }
}
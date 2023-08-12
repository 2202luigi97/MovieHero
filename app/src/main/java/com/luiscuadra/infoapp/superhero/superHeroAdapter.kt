package com.luiscuadra.infoapp.superhero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luiscuadra.infoApp.R

class superHeroAdapter ( var superHeroList:List<superHeroItem> = emptyList(), private val onItemSelected:(String) -> Unit) :
    RecyclerView.Adapter<superHeroViewHolder>() {

    fun udpateList(superHeroList: List<superHeroItem>){
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): superHeroViewHolder {
        return superHeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero,parent,false))
    }

    override fun getItemCount() = superHeroList.size


    override fun onBindViewHolder(viewholder: superHeroViewHolder, position: Int) {
        viewholder.bind(superHeroList[position], onItemSelected)
    }
}
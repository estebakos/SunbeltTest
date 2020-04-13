package com.estebakos.sunbelt.test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.estebakos.sunbelt.test.R
import com.estebakos.sunbelt.test.ui.model.AnimeListUI
import kotlinx.android.synthetic.main.item_anime.view.*

class AnimeRecyclerViewAdapter(
    private var values: List<AnimeListUI>,
    private val listener: (AnimeListUI) -> Unit
) : RecyclerView.Adapter<AnimeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anime, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvTitle.text = item.title
        holder.tvSynopsis.text = item.synopsis
        Glide.with(holder.view).load(item.imageUrl).into(holder.ivCover)

        with(holder.view) {
            setOnClickListener {
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tv_title_anime_list
        val tvSynopsis: TextView = view.tv_synopsis_anime_list
        val ivCover: ImageView = view.iv_anime_list
    }
}
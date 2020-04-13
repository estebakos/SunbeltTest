package com.estebakos.sunbelt.test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.estebakos.sunbelt.test.R
import com.estebakos.sunbelt.test.ui.model.WelcomeListUI
import kotlinx.android.synthetic.main.item_welcome.view.*

class WelcomeRecyclerViewAdapter(
    private val welcomeList: List<WelcomeListUI>,
    private val listener: WelcomeListener
) : RecyclerView.Adapter<WelcomeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_welcome, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = welcomeList[position]
        holder.run {
            tvTitle.text = item.title
            tvDescription.text = item.description
            ivRemove.setOnClickListener {
                listener.onCardRemoved(item)
            }
        }
    }

    override fun getItemCount(): Int = welcomeList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tv_welcome_title
        val tvDescription: TextView = view.tv_welcome_description
        val ivRemove: ImageView = view.iv_remove_card
    }

    interface WelcomeListener {
        fun onCardRemoved(item: WelcomeListUI)
    }
}
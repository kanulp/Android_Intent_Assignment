package com.kanulp.assignment1_intent

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AllCountiesAdapter (var context: Context, private var gitUsersArrayList: List<CountryModel>, var listner : AllCountiesAdapter.OnItemClickListener?) :
    RecyclerView.Adapter<AllCountiesAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int?)
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(context)
                    .inflate(R.layout.item_all_countries, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return gitUsersArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tv_country?.text = gitUsersArrayList[position].country
        holder.tv_lan?.text = gitUsersArrayList[position].language

        holder.itemView?.setOnClickListener {
            listner?.onItemClick(position)
        }
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(
            mView
    ) {
        var tv_country: TextView? =null
        var tv_lan: TextView? = null

        init {
            tv_country = mView.findViewById<View>(R.id.tv_title) as TextView
            tv_lan = mView.findViewById<View>(R.id.tv_language) as TextView
        }
    }
}
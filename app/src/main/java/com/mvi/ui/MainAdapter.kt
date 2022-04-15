package com.mvi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvi.R
import com.mvi.data.User

class MainAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    private lateinit var holder: DataViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        holder = DataViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bindView(holder, users[position])
    }

    override fun getItemCount(): Int = users.size

    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var username: AppCompatTextView = view.findViewById(R.id.textViewUserName)
        private var imageView: ImageView = view.findViewById(R.id.imageViewAvatar)

        fun bindView(holder: DataViewHolder, user: User) {
            holder.username.text = user.login
            Glide.with(itemView.context)
                .load(user.avatar_url)
                .into(holder.imageView)
        }
    }

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}
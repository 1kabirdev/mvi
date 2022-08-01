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
import com.mvi.databinding.ItemListBinding

class MainAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bindView(users[position])

    override fun getItemCount(): Int = users.size

    class DataViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(user: User) {
            with(binding) {
                textViewUserName.text = user.login
                Glide.with(itemView.context)
                    .load(user.avatar_url)
                    .into(imageViewAvatar)
            }
        }
    }

    fun addData(list: List<User>) = users.addAll(list)

}
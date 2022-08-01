package com.mvi.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvi.data.models.User
import com.mvi.databinding.ItemListBinding

class MainAdapter(
    private val users: ArrayList<User>,
    private val listener: OnClickListener
) :
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

    inner class DataViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(user: User) {
            with(binding) {
                textViewUserName.text = user.login
                Glide.with(itemView.context)
                    .load(user.avatar_url)
                    .into(imageViewAvatar)

                itemView.setOnClickListener {
                    listener.onClickUser(user.login)
                }
            }
        }
    }

    fun addData(list: List<User>) = users.addAll(list)


    interface OnClickListener {
        fun onClickUser(login: String)
    }

}
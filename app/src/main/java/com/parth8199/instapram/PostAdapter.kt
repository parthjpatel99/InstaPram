package com.parth8199.instapram

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(val context : Context, val posts: MutableList<Post>): RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    public fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }


    public fun addAll(postList: List<Post>) {
        posts.addAll(postList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername : TextView
        val tvFeedDescription : TextView
        val ivFeedImage : ImageView

        init {
            tvUsername = itemView.findViewById(R.id.tvUser)
            tvFeedDescription = itemView.findViewById(R.id.tvFeedDescription)
            ivFeedImage = itemView.findViewById(R.id.ivFeedPost)
        }

        fun bind(post : Post){
            tvFeedDescription.text = post.getDescription()
            tvUsername.text = post.getUser()?.username
            Glide.with(itemView.context).load(post.getImage()?.url).into(ivFeedImage)
        }
    }

}
package com.fivesol.kapsulemcqs.presentation.old

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fivesol.kapsulemcqs.R
import com.fivesol.kapsulemcqs.presentation.old.PostAdapter.PostViewHolder
import fivesol.networklibrary.domain.models.main_category.Data

class PostAdapter(private val context: Context, private val items: List<Data>, val mainCategoryListener :MainCategoryListener) :
    RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val (_, icon, _, name) = items[position]
        holder.postTitle.text = name
        Glide.with(context).load(icon).placeholder(context.getDrawable(R.mipmap.app_icon)).into(holder.postImage)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postImage: ImageView
        var postTitle: TextView

        init {
            postImage = itemView.findViewById<View>(R.id.postImage) as ImageView
            postTitle = itemView.findViewById<View>(R.id.postTitle) as TextView
            itemView.setOnClickListener { v ->
                mainCategoryListener.onMainCategoryClick(adapterPosition,items[adapterPosition].id!!)
            }
        }
    }
}
interface MainCategoryListener{
    fun onMainCategoryClick(categoryPosition:Int,categoryId:Int)
}
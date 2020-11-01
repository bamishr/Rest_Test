package io.indrian16.getrestapi.ui.post.rv

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.indrian16.getrestapi.R
import io.indrian16.getrestapi.model.Post
import kotlinx.android.synthetic.main.post_item.view.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostHolder>() {

    private var postList: List<Post> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {

        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.post_item, parent, false)
        return PostHolder(root)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        holder.bind(postList[position])
    }

    fun addPost(postList: List<Post>) {

        this.postList = postList
        notifyDataSetChanged()
    }

    inner class PostHolder(root: View) : RecyclerView.ViewHolder(root) {

        fun bind(post: Post) = with(itemView) {

            tv_post_title.text = capitalized(post.title)
            tv_post_body.text = capitalized(post.body)
        }
        
        private fun capitalized(valueStr: String): String {
            
            return valueStr.substring(0, 1).toUpperCase() + valueStr.substring(1).toLowerCase()
        }
    }
}
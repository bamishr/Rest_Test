package io.indrian16.getrestapi.ui.user.rv

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.indrian16.getrestapi.R
import io.indrian16.getrestapi.model.User
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var userList: List<User> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {

        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
        return UserHolder(root)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {

        holder.bind(userList[position])
    }

    fun addUser(userList: List<User>) {

        this.userList = userList
        notifyDataSetChanged()
    }

    inner class UserHolder(root: View) : RecyclerView.ViewHolder(root) {

        fun bind(user: User) = with(itemView) {

            tv_user_name.text = user.name
            tv_user_email.text = user.email
        }
    }
}
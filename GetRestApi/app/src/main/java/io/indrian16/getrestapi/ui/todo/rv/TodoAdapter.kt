package io.indrian16.getrestapi.ui.todo.rv

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.indrian16.getrestapi.R
import io.indrian16.getrestapi.model.Todo
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoHolder>() {

    private var todoList: List<Todo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {

        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_item, parent, false)
        return TodoHolder(root)
    }

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {

        holder.bind(todoList[position])
    }

    fun addTodo(todoList: List<Todo>) {

        this.todoList = todoList
        notifyDataSetChanged()
    }

    inner class TodoHolder(private val root: View) : RecyclerView.ViewHolder(root) {

        fun bind(todo: Todo) = with(itemView) {

            tv_todo_title.text = capitalize(todo.title)

            if (todo.completed)

                Picasso.get()
                        .load(R.drawable.check_square)
                        .into(img_todo_completed)

            else

                Picasso.get()
                        .load(R.drawable.new_file)
                        .into(img_todo_completed)
        }

        private fun capitalize(value: String): String {

            return value.substring(0,1).toUpperCase()+value.substring(1).toLowerCase()
        }
    }
}
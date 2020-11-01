package io.indrian16.getrestapi.ui.todo.view


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

import io.indrian16.getrestapi.R
import io.indrian16.getrestapi.model.Todo
import io.indrian16.getrestapi.ui.basefragment.BaseFragment
import io.indrian16.getrestapi.ui.todo.presenter.TodoPresenter
import io.indrian16.getrestapi.ui.todo.rv.TodoAdapter
import javax.inject.Inject

class TodoFragment : BaseFragment(), TodoView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    @Inject internal lateinit var presenter: TodoPresenter

    override fun onResume() {
        super.onResume()
        presenter.loadPost()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_todo, container, false)

        with(root) {

            recyclerView = findViewById(R.id.todo_recycler_view)
            progressBar = findViewById(R.id.todo_progressbar)
        }

        initView()
        return root
    }

    private fun initView() {

        recyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = TodoAdapter()
    }

    override fun showLoading() {

        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {

        progressBar.visibility = View.GONE
    }

    override fun updatePosts(todoList: List<Todo>) {

        (recyclerView.adapter as TodoAdapter).addTodo(todoList)
    }

    override fun showError(throwable: Throwable) {

        Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.unSubscribe()
    }
}

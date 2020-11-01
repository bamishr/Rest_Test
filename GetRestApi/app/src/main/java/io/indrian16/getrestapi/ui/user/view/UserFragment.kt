package io.indrian16.getrestapi.ui.user.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

import io.indrian16.getrestapi.R
import io.indrian16.getrestapi.model.User
import io.indrian16.getrestapi.ui.basefragment.BaseFragment
import io.indrian16.getrestapi.ui.user.presenter.UserPresenter
import io.indrian16.getrestapi.ui.user.rv.UserAdapter
import javax.inject.Inject

class UserFragment : BaseFragment(), UserView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    @Inject internal lateinit var presenter: UserPresenter

    override fun onResume() {
        super.onResume()
        presenter.loadUser()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_user, container, false)

        with(root) {

            recyclerView = findViewById(R.id.user_recycler_view)
            progressBar = findViewById(R.id.user_progressbar)
        }

        initView()
        return root
    }

    private fun initView() {

        recyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = UserAdapter()
    }

    override fun showLoading() {

        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {

        progressBar.visibility = View.GONE
    }

    override fun updatePosts(userList: List<User>) {

        (recyclerView.adapter as UserAdapter).addUser(userList)
    }

    override fun showError(throwable: Throwable) {

        Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show()
    }
}

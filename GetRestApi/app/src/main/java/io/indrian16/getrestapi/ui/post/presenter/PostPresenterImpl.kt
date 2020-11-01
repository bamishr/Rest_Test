package io.indrian16.getrestapi.ui.post.presenter

import io.indrian16.getrestapi.network.ApiService
import io.indrian16.getrestapi.ui.post.view.PostView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostPresenterImpl @Inject internal constructor(

        private val postView: PostView,
        private val apiService: ApiService) : PostPresenter {

    private var subscription: Disposable? = null

    override fun loadPost() {

        subscription = apiService
                .getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{postView.showLoading()}
                .doOnTerminate { postView.hideLoading() }
                .subscribe(

                        {postList -> postView.updatePost(postList)},
                        {error -> postView.showError(error)}
                )
    }

    private fun safelyDispose(disposable: Disposable?) {

        if (disposable != null && disposable.isDisposed) {

            disposable.dispose()
        }
    }

    override fun unSubscribe() {

        safelyDispose(subscription)
    }
}
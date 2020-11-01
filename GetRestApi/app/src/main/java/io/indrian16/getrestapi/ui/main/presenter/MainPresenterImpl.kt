package io.indrian16.getrestapi.ui.main.presenter

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.indrian16.getrestapi.ui.main.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenterImpl @Inject internal constructor(private val mainView: MainView) : MainPresenter {

    private var internetDisposable: Disposable? = null

    override fun checkInternetStatus() {

        internetDisposable = ReactiveNetwork
                .observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        {status ->

                            if (status) {

                                mainView.showOnlineStatus()
                            } else {

                                mainView.showOfflineStatus()
                            }
                        },
                        {error -> mainView.showError(error)}
                )
    }

    private fun safelyDispose(disposable: Disposable?) {

        if (disposable != null && disposable.isDisposed) {

            disposable.dispose()
        }
    }

    override fun unSubscribe() {

        safelyDispose(internetDisposable)
    }
}
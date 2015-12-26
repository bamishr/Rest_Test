package io.indrian16.getrestapi.ui.main.presenter

interface MainPresenter {

    fun checkInternetStatus()

    fun unSubscribe()
}
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }
}
package io.indrian16.getrestapi.ui.main.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.indrian16.getrestapi.R
import io.indrian16.getrestapi.ui.main.presenter.MainPresenter
import io.indrian16.getrestapi.util.CommonUtil
import io.indrian16.getrestapi.util.setupActionBar
import io.indrian16.getrestapi.util.setupViewPager
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView, HasSupportFragmentInjector {

    @Inject internal lateinit var presenter: MainPresenter

    @Inject internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        AndroidInjection.inject(this)
        presenter.checkInternetStatus()
    }

    private fun initView() {

        setupActionBar(R.id.toolbar) {

            supportActionBar?.title = resources.getString(R.string.app_name)
        }
        setupViewPager(R.id.view_pager, R.id.tab_layout)
    }

    override fun showOnlineStatus() {

        CommonUtil.showOnlineStatus(main_view, baseContext).show()
    }

    override fun showOfflineStatus() {

        CommonUtil.showOfflineStatus(main_view, baseContext).show()
    }

    override fun showError(error: Throwable) {

        Toast.makeText(baseContext, error.message, Toast.LENGTH_LONG).show()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {

        return dispatchingAndroidInjector
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }
}

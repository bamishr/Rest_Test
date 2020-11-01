package io.indrian16.getrestapi.util

import android.support.annotation.IdRes
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import io.indrian16.getrestapi.ui.main.rv.ViewPagerAdapter

fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {

    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {

        action()
    }
}


fun AppCompatActivity.setupViewPager(@IdRes viewPagerId: Int, @IdRes tabLayoutId: Int) {

    val viewPager = findViewById<ViewPager>(viewPagerId)
    val tabLayout = findViewById<TabLayout>(tabLayoutId)

    val adapter = ViewPagerAdapter(supportFragmentManager)
    viewPager.adapter = adapter
    tabLayout.setupWithViewPager(viewPager)
}
package io.indrian16.getrestapi.ui.main.view

interface MainView {

    fun showOnlineStatus()

    fun showOfflineStatus()

    fun showError(error: Throwable)
}
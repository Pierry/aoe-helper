package com.gjithub.pierry.aoehelper.core.presentation

import android.content.Context
import android.view.View
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.no_content_layout.*
import kotlinx.android.synthetic.main.no_internet_layout.*
import kotlinx.android.synthetic.main.server_unavailable_layout.*


open class BaseActivity : DaggerAppCompatActivity(), IBaseView {

    override fun onAttachedToWindow() {
        AndroidInjection.inject(this)
        super.onAttachedToWindow()
    }

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let { super.attachBaseContext(ViewPumpContextWrapper.wrap(it)) }
    }

    override fun noInternet() {
        noInternetIcon?.visibility = View.VISIBLE
        noInternetTitle?.visibility = View.VISIBLE
        noInternetMsg?.visibility = View.VISIBLE
    }

    override fun hasInternet() {
        noInternetIcon?.visibility = View.GONE
        noInternetTitle?.visibility = View.GONE
        noInternetMsg?.visibility = View.GONE
    }

    override fun noContent() {
        noContentIcon?.visibility = View.VISIBLE
        noContentTitle?.visibility = View.VISIBLE
        noContentMsg?.visibility = View.VISIBLE
    }

    override fun hasContent() {
        noContentIcon?.visibility = View.GONE
        noContentTitle?.visibility = View.GONE
        noContentMsg?.visibility = View.GONE
    }

    override fun serverUnavailable() {
        serverErrorIcon?.visibility = View.VISIBLE
        serverErrorTitle?.visibility = View.VISIBLE
        serverErrorMsg?.visibility = View.VISIBLE
    }

    override fun serverOk() {
        serverErrorIcon?.visibility = View.GONE
        serverErrorTitle?.visibility = View.GONE
        serverErrorMsg?.visibility = View.GONE
    }

}
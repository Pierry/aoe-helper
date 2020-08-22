package com.gjithub.pierry.aoehelper.core

import android.app.Application
import com.facebook.stetho.Stetho
import com.gjithub.pierry.aoehelper.R
import com.gjithub.pierry.aoehelper.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import javax.inject.Inject


class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        ViewPump.init(
            ViewPump.builder()
            .addInterceptor(
                CalligraphyInterceptor(
                    CalligraphyConfig.Builder()
                        .setDefaultFontPath("montserrat_regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
                )
            ).build()
        )
        di()
    }

    private fun di() {
        DaggerAppComponent.builder()
            .application(this)
            .builder()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}
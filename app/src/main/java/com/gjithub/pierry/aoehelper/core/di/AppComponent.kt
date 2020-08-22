package com.gjithub.pierry.aoehelper.core.di

import android.app.Application
import com.gjithub.pierry.aoehelper.core.App
import com.gjithub.pierry.aoehelper.core.di.modules.AppModule
import com.gjithub.pierry.aoehelper.core.di.modules.NetworkModule
import com.gjithub.pierry.aoehelper.core.di.ui.ActivityBuilder
import com.gjithub.pierry.aoehelper.core.di.ui.ViewBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AndroidInjectionModule::class,
    ActivityBuilder::class,
    AppModule::class,
    NetworkModule::class,
    ViewBuilder::class
  ]
)
interface AppComponent {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(app: Application): Builder

    fun builder(): AppComponent

  }

  fun inject(app: App)
}
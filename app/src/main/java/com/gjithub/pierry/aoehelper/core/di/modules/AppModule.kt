package com.gjithub.pierry.aoehelper.core.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module(subcomponents = [])
class AppModule {

  @Provides
  fun providesAppContext(app: Application): Context = app.applicationContext

}
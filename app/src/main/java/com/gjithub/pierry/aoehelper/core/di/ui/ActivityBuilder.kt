package com.gjithub.pierry.aoehelper.core.di.ui

import com.gjithub.pierry.aoehelper.core.MainActivity
import com.gjithub.pierry.aoehelper.home.view.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @View
  @ContributesAndroidInjector
  abstract fun bindMainActivity(): MainActivity

  @View
  @ContributesAndroidInjector
  abstract fun bindLoginFragment(): HomeFragment

}
package com.gjithub.pierry.aoehelper.core.di.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gjithub.pierry.aoehelper.home.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewBuilder {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  internal abstract fun providesLoginViewModel(viewModel: HomeViewModel): ViewModel

}
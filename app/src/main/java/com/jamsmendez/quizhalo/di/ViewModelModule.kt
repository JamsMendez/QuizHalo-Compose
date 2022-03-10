package com.jamsmendez.quizhalo.di

import com.jamsmendez.quizhalo.navegation.AppRouteNavigator
import com.jamsmendez.quizhalo.navegation.RouteNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

  @Provides
  @ViewModelScoped
  fun bindRouteNavigator(): RouteNavigator = AppRouteNavigator()
}
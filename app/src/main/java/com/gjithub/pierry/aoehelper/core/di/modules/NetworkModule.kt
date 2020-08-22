package com.gjithub.pierry.aoehelper.core.di.modules

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.gjithub.pierry.aoehelper.BuildConfig
import com.gjithub.pierry.aoehelper.core.api.IApi
import com.gjithub.pierry.aoehelper.core.common.SharedPref
import com.gjithub.pierry.aoehelper.home.model.HomeRepository
import com.gjithub.pierry.aoehelper.home.model.IHomeRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

  @Provides
  fun providesGson(): Gson =
    GsonBuilder().excludeFieldsWithModifiers(
      Modifier.FINAL,
      Modifier.TRANSIENT,
      Modifier.STATIC
    ).create()

  @Provides
  fun providesOkHttp(): OkHttpClient =
    OkHttpClient.Builder()
      .addNetworkInterceptor(StethoInterceptor())
      .connectTimeout(60000L, TimeUnit.MILLISECONDS)
      .readTimeout(60000L, TimeUnit.MILLISECONDS)
      .writeTimeout(60000L, TimeUnit.MILLISECONDS)
      .build()

  @Provides
  fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
      .baseUrl(BuildConfig.URL)
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .client(okHttpClient)
      .build()

  @Provides
  fun providesIApi(retrofit: Retrofit): IApi = retrofit.create(IApi::class.java)

  @Provides
  fun providesSharedPrefs(context: Context): SharedPref = SharedPref(context)

  @Provides
  fun providesHomeRepository(api: IApi): IHomeRepository = HomeRepository(api)

}
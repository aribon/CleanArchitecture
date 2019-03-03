package com.insign.clean.data.provider.network

import com.insign.clean.data.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by anthony.ribon
 * On 01/03/2019
 */
class RetrofitHelper {

  companion object {

    private const val TIMEOUT = 30000L
    private const val CACHE_SIZE = 1024

    fun buildRetrofit(
        apiUrl: String,
        timeOut: Long = TIMEOUT,
        cacheFile: File? = null): Retrofit {

      val httpClient = OkHttpClient.Builder()
          .addCache(cacheFile, CACHE_SIZE.toLong())
          .addTimeOut(timeOut, TIMEOUT)
          .addLoggerInterceptor()

      val retrofit = Retrofit.Builder()
          .baseUrl(apiUrl)
          .addRxAdapterFactory()
          .addGsonConverterFactory()
          .client(httpClient.build())
          .build()

      return retrofit
    }
  }
}

fun OkHttpClient.Builder.addLoggerInterceptor() = apply {
  addInterceptor(HttpLoggingInterceptor(ApiLogger()).setLevel(
      when {
        BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
        else -> HttpLoggingInterceptor.Level.NONE
      }))
}

fun OkHttpClient.Builder.addCache(file: File?, cacheSize: Long) = apply {
  file?.let {
    cache(Cache(it, cacheSize))
  }
}

fun OkHttpClient.Builder.addTimeOut(timeOut: Long, defaultTimeOut: Long) = apply {
  readTimeout(if (timeOut < defaultTimeOut) defaultTimeOut else timeOut, TimeUnit.MILLISECONDS)
  connectTimeout(if (timeOut < defaultTimeOut) defaultTimeOut else timeOut, TimeUnit.MILLISECONDS)
}

fun Retrofit.Builder.addGsonConverterFactory() = apply {
  addConverterFactory(GsonConverterFactory.create())
}

fun Retrofit.Builder.addRxAdapterFactory() = apply {
  addCallAdapterFactory(RxJava2CallAdapterFactory.create())
}
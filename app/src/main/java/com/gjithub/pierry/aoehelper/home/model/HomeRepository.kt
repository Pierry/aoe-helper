package com.gjithub.pierry.aoehelper.home.model

import com.gjithub.pierry.aoehelper.core.api.IApi
import kotlinx.coroutines.flow.flow
import java.net.HttpURLConnection

class HomeRepository(val api: IApi) : IHomeRepository {
  override suspend fun search(typed: String) = flow {
    val req = api.search(typed = typed)
    val res = req.await()
    when (res.code()) {
      HttpURLConnection.HTTP_OK -> res.body()
        ?.let { emit(Result.success(it)) } ?: emit(Result.failure(Exception("User not found")))
      else -> emit(Result.failure(Exception("User not found")))
    }
  }

  override suspend fun match(steamId: String) = flow {
    val req = api.match(steamId = steamId)
    val res = req.await()
    when (res.code()) {
      HttpURLConnection.HTTP_OK -> res.body()
        ?.let { emit(Result.success(it)) } ?: emit(Result.failure(Exception("Match not found")))
      else -> emit(Result.failure(Exception("Match not found")))
    }
  }

  override suspend fun matches(steamId: String) = flow {
    val req = api.history(steamId = steamId)
    val res = req.await()
    when (res.code()) {
      HttpURLConnection.HTTP_OK -> res.body()
        ?.let { emit(Result.success(it)) } ?: emit(Result.failure(Exception("Match not found")))
      else -> emit(Result.failure(Exception("Match not found")))
    }
  }

  override suspend fun civis() = flow {
    val req = api.civis()
    val res = req.await()
    when (res.code()) {
      HttpURLConnection.HTTP_OK -> res.body()
        ?.let { emit(Result.success(it)) } ?: emit(Result.failure(Exception("Civis not found")))
      else -> emit(Result.failure(Exception("Civis not found")))
    }
  }

}
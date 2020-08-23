package com.gjithub.pierry.aoehelper.core.api

import com.gjithub.pierry.aoehelper.core.domain.Civi
import com.gjithub.pierry.aoehelper.core.domain.Last
import com.gjithub.pierry.aoehelper.core.domain.Match
import com.gjithub.pierry.aoehelper.core.domain.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApi {

  @GET("/api/strings")
  fun civis(): Deferred<Response<Civi>>

  @GET("/api/leaderboard")
  fun search(
    @Query("game") game: String = "aoe2de",
    @Query("leaderboard_id") leaderboardId: Int = 3,
    @Query("start") startRank: Int = 1,
    @Query("count") size: Int = 1,
    @Query("search") typed: String
  ): Deferred<Response<User>>

  @GET("/api/player/lastmatch")
  fun match(
    @Query("game") game: String = "aoe2de",
    @Query("steam_id") steamId: String
  ): Deferred<Response<Match>>

  @GET("/api/player/matches")
  fun history(
    @Query("game") game: String = "aoe2de",
    @Query("start") startRank: Int = 0,
    @Query("count") count: Int = 30,
    @Query("steam_id") steamId: String
  ): Deferred<Response<List<Last>>>
}
package com.gjithub.pierry.aoehelper.core.domain

import com.google.gson.annotations.SerializedName

data class User(
    var id: Long,
    var leaderboard: List<Rating>
)

data class Rating(
    var rank: Int,
    var rating: Int,
    @SerializedName("steam_id") var steamId: String,
    var name: String,
    var country: String,
    var streak: Int,
    var games: Int,
    var wins: Int,
    var losses: Int
)
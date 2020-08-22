package com.gjithub.pierry.aoehelper.core.domain

import com.google.gson.annotations.SerializedName

data class Match(
  @SerializedName("last_match") var match: Last
)

data class Last(
  var players: List<Player>
)

data class Player(
  var name: String,
  var rating: Int
)
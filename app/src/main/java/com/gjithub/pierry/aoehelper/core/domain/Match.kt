package com.gjithub.pierry.aoehelper.core.domain

import com.google.gson.annotations.SerializedName

data class Match(
  @SerializedName("last_match") var match: Last
)

data class Last(
  var players: List<Player>,
  @SerializedName("num_players") var numPlayers: Int,
  @SerializedName("name") var name: String
) {
  fun isRanked(): Boolean {
    if (name == "AUTOMATCH"){
      return true
    }
    return false
  }

  fun isValid():Boolean{
    if (numPlayers == 2){
      return true
    }
    return false
  }
}

data class Player(
  var name: String,
  var rating: Int,
  var won: Boolean,
  var civ: Int,
  var civiName: String,
  var percentWon: Int
)
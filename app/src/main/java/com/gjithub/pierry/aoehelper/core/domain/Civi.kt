package com.gjithub.pierry.aoehelper.core.domain

import com.google.gson.annotations.SerializedName

data class Civi(
  var civ: List<Civ>
)

data class Civ(
  var id: Int,
  @SerializedName("string") var name: String
)

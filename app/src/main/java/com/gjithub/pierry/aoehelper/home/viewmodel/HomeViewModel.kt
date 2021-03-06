package com.gjithub.pierry.aoehelper.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gjithub.pierry.aoehelper.core.domain.Player
import com.gjithub.pierry.aoehelper.core.domain.Rating
import com.gjithub.pierry.aoehelper.home.model.IHomeRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  app: Application,
  val repository: IHomeRepository
) : ViewModel() {

  val myRating = MutableLiveData<Rating>()
  val player = MutableLiveData<Player>()
  val error = MutableLiveData<String>()
  val loading = MutableLiveData<Boolean>()
  val wonPercent = MutableLiveData<Int>()
  val descriptionCount = MutableLiveData<Double>()

  fun search(typed: String) {
    viewModelScope.launch {
      loading.postValue(true)
      repository.search(typed)
        .collect { result ->
          if (result.isSuccess) {
            result.getOrNull()
              ?.let { item ->
                item.leaderboard.firstOrNull()?.let { myRating.postValue(it) }
              } ?: loading.postValue(false)
          } else {
            result.exceptionOrNull()
              ?.let {
                loading.postValue(false)
                error.postValue(it.message)
              }
          }
        }
    }
  }

  fun match(steamId: String, name: String) {
    viewModelScope.launch {
      repository.match(steamId)
        .collect { result ->
          if (result.isSuccess) {
            result.getOrNull()
              ?.let { item ->
                val opponent = item.match.players.first { it.name != name }
                civi(opponent)
              }
          } else {
            result.exceptionOrNull()
              ?.let {
                error.postValue(it.message)
                loading.postValue(false)
              }
          }
        }
    }
  }

  fun civi(opponent: Player) {
    viewModelScope.launch {
      repository.civis().collect { result ->
        result.getOrNull()?.let { civis ->
          val civ = civis.civ.first { it.id == opponent.civ }
          opponent.civiName = civ.name
          player.postValue(opponent)
        }
      }
    }
  }

  fun matches(name: String, steamId: String) {
    viewModelScope.launch {
      repository.matches(steamId)
        .collect { result ->
          if (result.isSuccess) {
            result.getOrNull()
              ?.let { matches ->
                var count = 0.0
                var won = 0.0
                matches.forEach { match ->
                  if (match.isRanked() && match.isValid()) {
                    val opponent = match.players.first { it.name != name }
                    if (!opponent.won) {
                      won++
                    }
                    count++
                  }
                }
                descriptionCount.postValue(count)
                val average = (won * 100) / count
                wonPercent.postValue(average.toInt())
                loading.postValue(false)
              }
          } else {
            result.exceptionOrNull()
              ?.let {
                error.postValue(it.message)
                loading.postValue(false)
              }
          }
        }
    }
  }

}
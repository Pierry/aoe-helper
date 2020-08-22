package com.gjithub.pierry.aoehelper.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gjithub.pierry.aoehelper.core.common.SharedPref
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

  fun search(typed: String) {
    viewModelScope.launch {
      loading.postValue(true)
      repository.search(typed)
        .collect { result ->
          if (result.isSuccess) {
            result.getOrNull()
              ?.let { item -> myRating.postValue(item.leaderboard.first()) }
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
                player.postValue(opponent)
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
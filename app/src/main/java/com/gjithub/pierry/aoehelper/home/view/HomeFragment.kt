package com.gjithub.pierry.aoehelper.home.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.gjithub.pierry.aoehelper.core.common.SharedPref
import com.gjithub.pierry.aoehelper.core.common.snackbar
import com.gjithub.pierry.aoehelper.core.presentation.BaseFragment
import com.gjithub.pierry.aoehelper.databinding.HomeFragmentBinding
import com.gjithub.pierry.aoehelper.home.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeFragment : BaseFragment() {

  @Inject
  lateinit var factory: ViewModelProvider.Factory

  @Inject
  lateinit var sharePref: SharedPref

  private lateinit var viewModel: HomeViewModel
  private lateinit var binding: HomeFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    binding = HomeFragmentBinding.inflate(layoutInflater)
    binding.lifecycleOwner = this
//    binding.setVariable(BR., Login())
    binding.viewModel = viewModel
    return binding.layout
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    observers()
    hasSaved()
    listeners()
  }

  private fun listeners() {
    binding.searchIcon.setOnClickListener {
      if (binding.search.visibility == View.VISIBLE) {
        binding.search.visibility = View.GONE
        binding.ok.visibility = View.GONE
        binding.search.editText?.text = Editable.Factory.getInstance().newEditable("")
      } else {
        binding.search.visibility = View.VISIBLE
        binding.ok.visibility = View.VISIBLE
      }
    }
    binding.playing.setOnClickListener {
      hasSaved()
    }
  }

  private fun hasSaved() {
    sharePref.getString(SharedPref.NAME)
      ?.let {
        viewModel.search(it)
      }
  }

  private fun observers() {
    viewModel.myRating.observe(viewLifecycleOwner, {
      binding.myName.text = it.name
      binding.myRating.text = "Rating: ${it.rating}"
      sharePref.put(SharedPref.NAME, it.name)
      sharePref.put(SharedPref.RATING, it.rating)
      sharePref.put(SharedPref.STEAM_ID, it.steamId)
      getMatch(it.steamId, it.name)
    })
    viewModel.error.observe(viewLifecycleOwner, { error ->
      binding.layout.snackbar(error)
    })
    viewModel.player.observe(viewLifecycleOwner, { player ->
      binding.rating.text = player.rating.toString()
      binding.name.text = player.name
      binding.civi.text = player.civiName
    })
    viewModel.loading.observe(viewLifecycleOwner, { loading ->
      if (loading) {
        binding.progressBarGen.visibility = View.VISIBLE
      } else {
        binding.progressBarGen.visibility = View.GONE
      }
    })
    viewModel.wonPercent.observe(viewLifecycleOwner, {
      binding.percent.text = "${it.toString()}%"
    })
    viewModel.descriptionCount.observe(viewLifecycleOwner, {
      binding.description.text = "WIN IN LAST\n${it.toInt()} RANKED 1v1 MATCHES"
    })
  }

  private fun getMatch(steamId: String, name: String) {
    lifecycleScope.launchWhenCreated {
      viewModel.match(steamId, name)
      viewModel.matches(name, steamId)
    }
  }
}
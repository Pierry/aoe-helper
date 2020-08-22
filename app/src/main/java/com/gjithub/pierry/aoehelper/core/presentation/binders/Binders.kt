package br.com.bustop.core.presentation.binders

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class Binders {
  companion object {
    @JvmStatic
    @BindingAdapter("listData")
    fun bindStopRecyclerView(
      recyclerView: RecyclerView,
      listData: List<Any>?
    ) {
      val adapter = recyclerView.adapter as RecyclerView.Adapter<*>
//      adapter.submitList(listData)
    }
  }
}
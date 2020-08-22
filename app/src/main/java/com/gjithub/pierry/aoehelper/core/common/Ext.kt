package com.gjithub.pierry.aoehelper.core.common

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import java.io.ByteArrayOutputStream

fun Fragment.onNavigate(directions: NavDirections) {
  findNavController().navigate(directions)
}

fun View.snackbar(message: CharSequence) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).apply { show() }

fun Bitmap.toByteArray(): ByteArray {
  val stream = ByteArrayOutputStream()
  this.compress(Bitmap.CompressFormat.PNG, 100, stream)
  return stream.toByteArray()
}

fun ImageView.transform(transformation: Transformation?, url: String, cropped: Boolean) {
  if (transformation == null) {
    if (cropped) {
      Picasso.get().load(url).centerCrop().fit().into(this)
    } else {
      Picasso.get().load(url).fit().into(this)
    }
  } else {
    if (cropped) {
      Picasso.get().load(url).centerCrop().fit().transform(transformation).into(this)
    } else {
      Picasso.get().load(url).fit().transform(transformation).into(this)
    }
  }
}

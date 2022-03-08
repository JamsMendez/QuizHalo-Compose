package com.jamsmendez.quizhalo.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TextFieldViewModel @Inject constructor(): ViewModel() {
  private val _text = mutableStateOf("")

  val text: State<String> = _text

  fun updateText(value: String) {
    _text.value = value
  }
}
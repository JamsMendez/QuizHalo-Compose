package com.jamsmendez.quizhalo.ui.viewmodel

import com.jamsmendez.quizhalo.model.ScoreModel

data class ScoreListState(
  val isLoading: Boolean = false,
  val scores: List<ScoreModel> = emptyList(),
  val error: String = ""
)

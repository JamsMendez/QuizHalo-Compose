package com.jamsmendez.quizhalo.ui.viewmodel

import com.jamsmendez.quizhalo.model.ScoreModel

data class ScoreState (
  val isLoading: Boolean = false,
  val score: ScoreModel = ScoreModel(),
  val error: String = ""
)
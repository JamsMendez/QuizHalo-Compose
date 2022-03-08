package com.jamsmendez.quizhalo.ui.viewmodel

import com.jamsmendez.quizhalo.model.QuestionModel

data class QuestionListState(
  val isLoading: Boolean = false,
  val questions: List<QuestionModel> = emptyList(),
  val error: String = ""
)

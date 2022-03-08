package com.jamsmendez.quizhalo.model

data class QuestionModel(
  val id: String = "",
  val content: String = "",
  var options: List<AnswerModel> = listOf(),

  var answered: Boolean = false
)

data class AnswerModel(
  val content: String = "",
  val correct: Boolean = false,

  var selected: Boolean = false
)
package com.jamsmendez.quizhalo.model

import com.google.firebase.firestore.DocumentId

data class QuestionModel(
  @DocumentId
  val id: String = "",
  val content: String = "",
  var options: List<AnswerModel> = listOf(),

  var answered: Boolean = false
)
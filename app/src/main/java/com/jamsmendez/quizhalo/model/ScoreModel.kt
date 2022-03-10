package com.jamsmendez.quizhalo.model

import com.google.firebase.firestore.DocumentId

data class ScoreModel(
  @DocumentId
  var id: String = "",
  val username: String = "",
  val points: Int = 0
)

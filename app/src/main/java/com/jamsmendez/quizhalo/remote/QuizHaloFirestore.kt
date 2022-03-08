package com.jamsmendez.quizhalo.remote

import com.google.firebase.firestore.CollectionReference

data class QuizHaloFirestore(
  val questions: CollectionReference,
  val scores: CollectionReference
)
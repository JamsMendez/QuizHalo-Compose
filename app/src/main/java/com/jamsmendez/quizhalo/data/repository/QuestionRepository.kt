package com.jamsmendez.quizhalo.data.repository

import com.google.firebase.firestore.CollectionReference
import com.jamsmendez.quizhalo.model.QuestionModel
import com.jamsmendez.quizhalo.remote.QuizHaloFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRepository
  @Inject constructor(
    private val quizHaloFirestore: QuizHaloFirestore
) {
  fun getQuestionList(): Flow<Result<List<QuestionModel>>> = flow {
    try {
      emit(Result.Loading<List<QuestionModel>>())

      val questionList = quizHaloFirestore.questions.get().await().map { document ->
        document.toObject(QuestionModel::class.java)
      }

      emit(Result.Success<List<QuestionModel>>(data = questionList))

    } catch (e: Exception) {
      emit(Result.Error<List<QuestionModel>>(
        message = e.localizedMessage ?: "Error desconicido")
      )
    }
  }
}
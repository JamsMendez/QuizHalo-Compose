package com.jamsmendez.quizhalo.data.repository

import android.util.Log
import com.google.firebase.firestore.Query
import com.jamsmendez.quizhalo.model.ScoreModel
import com.jamsmendez.quizhalo.remote.QuizHaloFirestore
import com.jamsmendez.quizhalo.util.Constants.FIELD_POINTS
import com.jamsmendez.quizhalo.util.Constants.FIELD_USERNAME
import com.jamsmendez.quizhalo.util.Constants.RANKING_LIMIT
import com.jamsmendez.quizhalo.util.Labels.ERROR_UNKNOWN
import com.jamsmendez.quizhalo.util.Labels.MESSAGE_EQUAL_POINTS
import com.jamsmendez.quizhalo.util.Labels.MESSAGE_LESS_POINTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScoreRepository
  @Inject constructor(
    private val quizHaloFirestore: QuizHaloFirestore
) {

  fun addScore(scoreIn: ScoreModel): Flow<Result<ScoreModel>> = flow {
    try {
      emit(Result.Loading<ScoreModel>())

      val score = quizHaloFirestore.scores
        .whereEqualTo(FIELD_USERNAME, scoreIn.username)
        .get()
        .await()
        .toObjects(ScoreModel::class.java)
        .firstOrNull()

      if (score != null) {
        when {
          score.points < scoreIn.points -> {
            scoreIn.id = score.id
            quizHaloFirestore.scores.document(scoreIn.id).set(scoreIn).await()
            emit(Result.Success<ScoreModel>(scoreIn))
          }

          score.points == scoreIn.points -> {
            emit(Result.Error<ScoreModel>(MESSAGE_EQUAL_POINTS, score))
          }
          else -> {
            emit(Result.Error<ScoreModel>(MESSAGE_LESS_POINTS, score))
          }
        }

      } else {
        scoreIn.id = quizHaloFirestore.scores.document().id

        quizHaloFirestore.scores.document(scoreIn.id).set(scoreIn).await()
        emit(Result.Success<ScoreModel>(scoreIn))
      }

    } catch (e: Exception) {
      emit(Result.Error<ScoreModel>(
        message = e.localizedMessage ?: ERROR_UNKNOWN)
      )
    }
  }

  fun getScoreList(): Flow<Result<List<ScoreModel>>> = flow {
    try {
      emit(Result.Loading<List<ScoreModel>>())

      val scoreList = quizHaloFirestore.scores
        .orderBy(FIELD_POINTS, Query.Direction.DESCENDING)
        .limit(RANKING_LIMIT.toLong())
        .get().await().map { document ->
        document.toObject(ScoreModel::class.java)
      }

      emit(Result.Success<List<ScoreModel>>(data = scoreList))

    } catch (e: Exception) {
      emit(Result.Error<List<ScoreModel>>(
        message = e.localizedMessage ?: ERROR_UNKNOWN)
      )
    }
  }
}

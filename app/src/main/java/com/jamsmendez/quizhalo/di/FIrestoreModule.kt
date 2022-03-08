package com.jamsmendez.quizhalo.di

import com.google.firebase.firestore.FirebaseFirestore
import com.jamsmendez.quizhalo.remote.QuizHaloFirestore
import com.jamsmendez.quizhalo.util.Constants.QUESTIONS_COLLECTION
import com.jamsmendez.quizhalo.util.Constants.SCORES_COLLECTION
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirestoreModule {
  @Provides
  @Singleton
  fun providerFirestore() = FirebaseFirestore.getInstance()

  @Provides
  @Singleton
  fun provideQuestionList(firestore: FirebaseFirestore): QuizHaloFirestore {
    return QuizHaloFirestore(
      questions = firestore.collection(QUESTIONS_COLLECTION),
      scores = firestore.collection(SCORES_COLLECTION)
    )
  }
}
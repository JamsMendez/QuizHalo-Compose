package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jamsmendez.quizhalo.ui.viewmodel.TextFieldViewModel
import com.jamsmendez.quizhalo.util.Labels.BTN_SIGN_IN

@Composable
fun SignInForm(
  modifier: Modifier = Modifier,
  textFieldViewModel: TextFieldViewModel = hiltViewModel(),
  onSignIn: (username: String) -> Unit = {}
) {
  val text by textFieldViewModel.text

  Column(
    modifier = modifier
      .wrapContentSize()
      .padding(horizontal = 16.dp)
  ) {
    SimpleTextField(
      text = text,
      onChangeText = { value ->
        textFieldViewModel.updateText(value)
      },
      onSuccess = {
        onSignIn(text.trim())
      }
    )
    Spacer(modifier = Modifier
      .height(16.dp)
      .fillMaxWidth())
    SimpleButton(
      modifier = Modifier.fillMaxWidth(),
      text = BTN_SIGN_IN,
      onClick = {
        if (text.isNotEmpty()) onSignIn(text.trim())
      }
    )
  }
}
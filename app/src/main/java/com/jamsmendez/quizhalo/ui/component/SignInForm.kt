package com.jamsmendez.quizhalo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jamsmendez.quizhalo.ui.theme.QuizHaloTheme
import com.jamsmendez.quizhalo.ui.viewmodel.TextFieldViewModel
import com.jamsmendez.quizhalo.util.Labels.BTN_SIGN_IN

@Composable
fun SignInForm(
  modifier: Modifier = Modifier,
  textFieldViewModel: TextFieldViewModel = hiltViewModel(),
  onSignInClicked: (username: String) -> Unit = {}
) {
  val text by textFieldViewModel.text

  Column(
    modifier = modifier
      .wrapContentSize()
  ) {
    SimpleTextField(
      text = text,
      onChangeText = { value ->
        textFieldViewModel.updateText(value)
      },
      onSuccess = { onSignInClicked(text) }
    )
    Spacer(modifier = Modifier
      .height(16.dp)
      .fillMaxWidth()
    )
    SimpleButton(
      modifier = Modifier.fillMaxWidth(),
      text = BTN_SIGN_IN,
      onClick = { onSignInClicked(text) }
    )
  }
}

@Composable
@Preview(showBackground = true)
fun SignInFormPreview() {
  QuizHaloTheme {
    SignInForm(
      modifier = Modifier.background(Color.Black)
    )
  }
}
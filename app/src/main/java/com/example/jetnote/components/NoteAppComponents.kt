package com.example.jetnote.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDefaults.contentColor
import androidx.compose.material3.TabRowDefaults.contentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import org.w3c.dom.Text


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NoteInputText(
    modifier: Modifier=Modifier ,
    text:String ,
    label:String ,
    maxLine:Int =1 ,
    onTextChange:(String)->Unit,
    onImeAction: () -> Unit = {}){

    val keyboardController=LocalSoftwareKeyboardController.current


    TextField(
            value = text,
            onValueChange = onTextChange,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.DarkGray ,
                focusedLabelColor = Color.DarkGray,
                focusedIndicatorColor = Color(0xFFDFE6EB),
                unfocusedIndicatorColor = Color(0xFFDFE6EB)
            ),
            maxLines = maxLine,
            label = {Text(text = label)},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                onImeAction()
                keyboardController?.hide()
            }),
            modifier = modifier
        )
}

@Composable
fun NoteButton(
      modifier: Modifier=Modifier,
      text:String,
      onClick:()->Unit ,
      enabled:Boolean=true

  ){
           Button(
               modifier = modifier,
               onClick =onClick,
               enabled=enabled,
               colors = ButtonDefaults.buttonColors(Color(0xFF5F87E9))

           ) {
                Text(text =text , color = Color.White )
           }
  }
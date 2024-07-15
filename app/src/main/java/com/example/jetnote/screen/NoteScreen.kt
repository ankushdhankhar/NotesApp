package com.example.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.Note
import com.example.jetnote.util.formatDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes:List<Note> ,
    onAddNote:(Note)->Unit ,
    onRemoveNote:(Note)->Unit

) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current


  Column {

      TopAppBar(
          modifier =Modifier.padding(horizontal = 5.dp , vertical = 1.dp ),
          title = { Text(text = "Notes") },
          actions = {
              Icon(
                  modifier = Modifier.padding(horizontal = 12.dp),
                  imageVector = Icons.Rounded.Notifications,
                  contentDescription = "notification icon"
              )
          },
          colors = TopAppBarDefaults.topAppBarColors(
              containerColor = Color(0xFFDADFE3)
          )
      )

      Column(modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally) {

          NoteInputText(
              modifier = Modifier.padding(top = 40.dp , bottom = 8.dp),
              text = title,
              label ="Title" ,
              onTextChange ={
                  if (it.all { char->
                      char.isLetter()||char.isWhitespace()
                      }) title=it
              }
          )

          NoteInputText(
              modifier = Modifier.padding(top = 9.dp , bottom = 25.dp ),
              text = description,
              label ="Add a Note" ,
              onTextChange ={
                  if (it.all { char->
                          char.isLetter()||char.isWhitespace()
                      }) description=it
              }
          )

          NoteButton(
              text = "Save",
              onClick = {
                  if (title.isNotEmpty()&& description.isNotEmpty()) {
                      onAddNote(Note(title = title, description = description))
                      title = ""
                      description = ""

                      Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                  }
              })

      }

      Divider(modifier=Modifier.padding(15.dp) , thickness = 2.dp)

      if (notes.isNotEmpty()){

          Text(text = "Saved Notes" ,
              style = MaterialTheme.typography.titleLarge ,
              modifier = Modifier.padding(start = 118.dp , bottom = 10.dp) ,
              fontWeight = FontWeight.ExtraBold)
      }



      LazyColumn(modifier=Modifier.padding(horizontal = 5.dp )) {

          items(notes) {
              note ->
              NoteRow(note = note, onNoteClicked ={
                  onRemoveNote(it)
              } )

          }
      }

  }

}


@Composable
fun NoteRow(
    modifier: Modifier=Modifier ,
    note: Note,
    onNoteClicked:(Note)->Unit
){


    Surface(
        modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)),
        color=Color(0xFFDFE6EB) ,
        shadowElevation = 6.dp
    ) {
        Column(modifier= Modifier
            .padding(start = 15.dp, bottom = 6.dp)
            .clickable { onNoteClicked(note) } ,
            horizontalAlignment = Alignment.Start) {
            Text(text = note.title , style=MaterialTheme.typography.titleLarge)
            Text(text = note.description , style=MaterialTheme.typography.titleMedium)
            Text(text = formatDate(note.entryDate.time) , style=MaterialTheme.typography.labelSmall)
        }

    }

}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NoteScreen(
        notes = NotesDataSource().LoadNotes(),
        onAddNote = {},
        onRemoveNote = {}
    )
}









//Hilt-Dagger
//implementation "com.google.dagger:hilt-android:$hilt_version"
//kapt "com.google.dagger:hilt-compiler:$hilt_version"

//Room
//implementation "androidx.room:room-runtime:$room_version"
//annotationProcessor "androidx.room:room-compiler:$room_version"

// To use Kotlin annotation processing tool (kapt) MUST HAVE!
//kapt("androidx.room:room-compiler:$room_version")
//implementation "androidx.room:room-ktx:$room_version"

// Coroutines
//implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
//implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
//implementation "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.5.2"

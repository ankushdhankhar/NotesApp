package com.example.jetnote.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.Note
import com.example.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) :ViewModel(){

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList=_noteList.asStateFlow()
//    private var noteList = mutableStateListOf<Note>()

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllNotes().distinctUntilChanged()
                .collect{ listOfNotes->
                    if(listOfNotes.isNullOrEmpty()){
                        Log.d("empty", ": Empty list")
                    }
                    else{
                        _noteList.value=listOfNotes
                    }

                 }
        }
//        noteList.addAll(NotesDataSource().LoadNotes())
    }

    fun addNote(note: Note)=viewModelScope.launch { repository.addNote(note) }
     fun removeNote(note: Note)=viewModelScope.launch { repository.deleteNote(note) }
     fun updateNote(note: Note)=viewModelScope.launch { repository.updateNote(note) }

}
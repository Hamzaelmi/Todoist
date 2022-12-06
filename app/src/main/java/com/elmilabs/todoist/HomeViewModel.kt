package com.elmilabs.todoist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val noteData = listOf(
        NoteUI("", "test content andoird".repeat(5), 0),
        NoteUI("", "test adsfkm dsf., dfsaml andoird".repeat(50), 0),
        NoteUI("", "tedsfma mfds l.fd doird".repeat(20), 0),
        NoteUI("", "test content ldjknsffa ".repeat(1000), 0),
        NoteUI(
            "", "test codfsakmflkds .dsf kdsmبيسنةم بةثصينخبةثصهبثص نةمبشيستىاعه ةنسىهعبا rd", 0
        )
    )
    val _allNotesFlow = MutableStateFlow(noteData)
    val allNotes get() = _allNotesFlow

    init {
        getAllNotes()
    }

    fun getAllNotes() {

    }

}
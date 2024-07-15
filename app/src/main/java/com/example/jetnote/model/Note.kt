package com.example.jetnote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes_tbl")
data class Note(

        @PrimaryKey
        val id: UUID = UUID.randomUUID() ,

        @ColumnInfo(name = "note_title")
        val title: String ,

        @ColumnInfo(name = "note_description")
        val description: String ,

        @ColumnInfo(name = "note_entry_Date")
        val entryDate: Date=Date.from(Instant.now())

    )
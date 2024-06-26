package com.dicoding.asclepius.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "bookmark_data")
@Parcelize
data class History(
    @PrimaryKey(autoGenerate = false)
    var image: String = "",
    var label: String = "",
    var score: String? = null,
) : Parcelable
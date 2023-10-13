package com.codepelita.challengeroom1.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tbsiswa (
        @PrimaryKey
        val nis : Int=0,
        val nama : String,
        val kelas : String,
        val alamat : String
        )


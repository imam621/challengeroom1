package com.codepelita.challengeroom1.room

import androidx.room.*

@Dao
interface tbsiswaDao {
    @Insert
    suspend fun addtbsiswa(tbsis : tbsiswa)
    @Update
    suspend fun updatetbsiswa(tbsis : tbsiswa)
    @Delete
    suspend fun deltbsiswa(tbsis : tbsiswa)

    @Query("SELECT * FROM tbsiswa ORDER BY nama ASC")
    suspend fun tampilsemua(): List<tbsiswa>

    @Query ("SELECT * FROM tbsiswa Where nis = :tbsis_nis")
    suspend fun tampilid(tbsis_nis: Int): List<tbsiswa>

}
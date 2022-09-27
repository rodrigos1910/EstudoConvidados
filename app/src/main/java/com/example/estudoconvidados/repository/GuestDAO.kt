package com.example.estudoconvidados.repository

import androidx.room.*
import com.example.estudoconvidados.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun insert(guest: GuestModel) : Long

    @Update
    fun update(guest: GuestModel)  : Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guest")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = :presence")
    fun getWithFilter(presence: Int): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun get(id: Int): GuestModel
}
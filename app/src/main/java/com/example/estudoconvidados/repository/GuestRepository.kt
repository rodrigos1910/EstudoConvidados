package com.example.estudoconvidados.repository

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import com.example.estudoconvidados.constants.DataBaseConstants
import com.example.estudoconvidados.model.GuestModel
import java.lang.Exception

class GuestRepository(context: Context) {

    //Singleton - controlar as instacias ao banco de dados, meio que controlar as conexoes com o BD

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()


    fun insert(guest: GuestModel): Boolean {

        return guestDataBase.insert(guest) > 0

    }


    fun update(guest: GuestModel): Boolean {
        return guestDataBase.update(guest) > 0


    }


    fun delete(guest: GuestModel): Boolean {

         guestDataBase.delete(guest)

        return true


    }


    fun getAll(): List<GuestModel> {

        return guestDataBase.getAll()

    }


    fun getWithFilter(presence: Int): List<GuestModel> {

        return guestDataBase.getWithFilter(presence)

    }


    fun get(id: Int): GuestModel {
        return guestDataBase.get(id)
    }



}
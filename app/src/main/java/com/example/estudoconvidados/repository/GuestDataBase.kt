package com.example.estudoconvidados.repository

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.estudoconvidados.model.GuestModel



@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase() : RoomDatabase() {


    abstract fun guestDAO(): GuestDAO

    companion object {

        //syngleton
        private lateinit var instance: GuestDataBase

        fun getDataBase(context: Context) : GuestDataBase{
            if (!::instance.isInitialized){
                synchronized(GuestDataBase::class){ //evita que treads executem a instancia ao mesmo tempo
                    instance = return androidx.room.Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .addMigrations(MIGRATION_1_2) //adiciona a opção de scripts para atualizações de versões
                        .allowMainThreadQueries()
                        .build()
                }

            }

            return instance
        }


        private val MIGRATION_1_2: Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Guest")
            }

        }

    }


}
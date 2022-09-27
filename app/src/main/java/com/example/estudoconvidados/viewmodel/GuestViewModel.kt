package com.example.estudoconvidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.estudoconvidados.model.GuestModel
import com.example.estudoconvidados.repository.GuestRepository

class GuestViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application.applicationContext)
    private val listAllGuest = MutableLiveData<List<GuestModel>>()


    val guests: LiveData<List<GuestModel>> = listAllGuest


    fun getAll(){
        listAllGuest.value =  repository.getAll()
    }

    fun getWithFilter(presence: Int){
        listAllGuest.value =  repository.getWithFilter(presence)
    }


    fun delete(id: Int){
        repository.delete(repository.get(id))
    }
}
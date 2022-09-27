package com.example.estudoconvidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.estudoconvidados.model.GuestModel
import com.example.estudoconvidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel


    private val _saveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = _saveGuest

    fun insert(guest: GuestModel){
        repository.insert(guest)
    }

    fun update(guest: GuestModel){
        repository.update(guest)
    }

    fun save(guest: GuestModel){
        if (guest.id > 0){
            _saveGuest.value = repository.update(guest)
        }else{
            _saveGuest.value = repository.insert(guest)
        }
    }

    fun delete(guest: GuestModel){
        repository.delete(guest.id)
    }

    fun get(id: Int){
        guestModel.value = repository.get(id)
    }




}
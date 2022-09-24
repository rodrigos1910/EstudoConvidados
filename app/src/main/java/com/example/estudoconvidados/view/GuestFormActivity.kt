package com.example.estudoconvidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.estudoconvidados.model.GuestModel
import com.example.estudoconvidados.R
import com.example.estudoconvidados.databinding.ActivityGuestFormBinding
import com.example.estudoconvidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel : GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)


        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

    }

    override fun onClick(view: View) {
       when (view.id){
           R.id.button_save ->{

                val name = binding.editNome.text.toString()
                val presence = binding.radioPresent.isChecked


                viewModel.insert( GuestModel(0, name,presence))



           }
       }
    }
}
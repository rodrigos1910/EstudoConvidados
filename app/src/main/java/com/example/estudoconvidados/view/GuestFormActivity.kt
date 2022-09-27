package com.example.estudoconvidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.estudoconvidados.model.GuestModel
import com.example.estudoconvidados.R
import com.example.estudoconvidados.constants.DataBaseConstants
import com.example.estudoconvidados.databinding.ActivityGuestFormBinding
import com.example.estudoconvidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel : GuestFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)


        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe()
        loadData()

    }

    override fun onClick(view: View) {
       when (view.id){
           R.id.button_save ->{

                val name = binding.editNome.text.toString()
                val presence = binding.radioPresent.isChecked


                viewModel.save( GuestModel(guestId, name,presence))

                finish()

           }
       }
    }

    private fun loadData(){
        var bundle = intent.extras

        if(bundle != null){

            guestId =  bundle.getInt(DataBaseConstants.GUEST.COLUMNS.ID)

            viewModel.get(guestId)

        }

    }

    fun observe(){
        viewModel.guest.observe(this, Observer {
            binding.editNome.setText(it.name)
            if (it.presence){
                binding.radioPresent.isChecked = true
            }else{
                binding.radioAbsent.isChecked = true
            }

        })

        viewModel.saveGuest.observe(this, Observer {
            if (it){
                   Toast.makeText(this, "Registro salvo com sucesso", Toast.LENGTH_SHORT).show()

                   finish()

            }else{
                Toast.makeText(this, "Falha ao salvar registro", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
package com.example.estudoconvidados.view.adapter.viewholder

import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.estudoconvidados.databinding.RowGuestBinding
import com.example.estudoconvidados.model.GuestModel
import com.example.estudoconvidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root){


    fun bind(guest:GuestModel){

        bind.textName.text = guest.name

        bind.textName.setOnClickListener{
            listener.OnClick(guest.id)
        }

        bind.textName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Convidado!")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim"
                ) { p0, p1 -> listener.OnDelete(guest.id) }
                .setNegativeButton("Não"
                ) { p0, p1 ->  }
                .create().show()


             true

        }


    }

}
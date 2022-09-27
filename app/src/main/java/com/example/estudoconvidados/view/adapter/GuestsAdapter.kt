package com.example.estudoconvidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.estudoconvidados.databinding.RowGuestBinding
import com.example.estudoconvidados.model.GuestModel
import com.example.estudoconvidados.view.adapter.viewholder.GuestViewHolder
import com.example.estudoconvidados.view.listener.OnGuestListener

class GuestsAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener : OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {

        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        return GuestViewHolder(item,listener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }


    fun updateGuests(list : List<GuestModel>){
        guestList = list
        notifyDataSetChanged() // faz com que a Recycle se crie automaticamente quando receber novos dados
    }


    fun attachListener(guestListener: OnGuestListener){
        listener = guestListener
    }
}
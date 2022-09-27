package com.example.estudoconvidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.estudoconvidados.constants.DataBaseConstants
import com.example.estudoconvidados.databinding.FragmentAbsentBinding
import com.example.estudoconvidados.view.adapter.GuestsAdapter
import com.example.estudoconvidados.view.listener.OnGuestListener
import com.example.estudoconvidados.viewmodel.GuestViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null

    private val binding get() = _binding!!


    private lateinit var viewModel: GuestViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(GuestViewModel::class.java)

        _binding = FragmentAbsentBinding.inflate(inflater, container, false)


        //definir um layout
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)

        //definir o adapter
        binding.recyclerGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun OnClick(id: Int) {

                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()

                bundle.putInt(DataBaseConstants.GUEST.COLUMNS.ID, id)

                intent.putExtras(bundle)


                startActivity(intent)

            }

            override fun OnDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getWithFilter(0)
            }

        }


        adapter.attachListener(listener)


        viewModel.getWithFilter(0)

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume(){
        super.onResume()
        viewModel.getWithFilter(0)
    }


    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)


        }
    }
}
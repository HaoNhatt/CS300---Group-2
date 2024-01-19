package com.example.movieticket.user.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieticket.R
import com.example.movieticket.user.data.UserViewModel

class CustomerTheaterAdapter(private val viewModel: UserViewModel): RecyclerView.Adapter<CustomerTheaterAdapter.TheaterViewHolder>() {

    class TheaterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val theaterNameView = view.findViewById<TextView>(R.id.theaterItemName)!!
        val theaterAddressView = view.findViewById<TextView>(R.id.theaterItemAddress)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheaterViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_theater_item, parent, false)

        return TheaterViewHolder(layout)
    }

    override fun getItemCount(): Int = viewModel.theaterList.size

    override fun onBindViewHolder(holder: TheaterViewHolder, position: Int) {
        holder.theaterNameView.text = viewModel.theaterList[position].name
        holder.theaterAddressView.text = viewModel.theaterList[position].address
        holder.itemView.setOnClickListener {
            viewModel.selectedTheaterIndex = position
            it.findNavController().navigate(R.id.action_customerChooseTheaterFragment_to_customerChooseScheduleFragment)
        }
    }
}
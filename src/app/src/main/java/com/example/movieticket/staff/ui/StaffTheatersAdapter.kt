package com.example.movieticket.staff.ui

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.movieticket.R

import com.example.movieticket.staff.data.StaffViewModel;

class StaffTheatersAdapter(private val viewModel: StaffViewModel): RecyclerView.Adapter<StaffTheatersAdapter.TheaterViewHolder>() {

    class TheaterViewHolder(private val view:View) : RecyclerView.ViewHolder(view) {
        val theaterNameView = view.findViewById<TextView>(R.id.staffTheaterItem)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheaterViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.staff_theaters_item, parent, false)

        return TheaterViewHolder(layout)
    }

    override fun getItemCount(): Int = viewModel.theatersList.size

    override fun onBindViewHolder(holder: TheaterViewHolder, position: Int) {
        holder.theaterNameView.text = viewModel.theatersList[position].name
        holder.itemView.setOnClickListener {
            viewModel.selectedTheaterIndex = position
            it.findNavController().navigate(R.id.action_staffTheatersFragment_to_theaterDetailFragment)
        }
    }
}
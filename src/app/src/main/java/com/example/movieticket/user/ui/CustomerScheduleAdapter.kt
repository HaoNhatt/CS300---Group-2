package com.example.movieticket.user.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieticket.R
import com.example.movieticket.user.data.UserViewModel

class CustomerScheduleAdapter(private val viewModel: UserViewModel): RecyclerView.Adapter<CustomerScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val scheduleNameView = view.findViewById<TextView>(R.id.scheduleDate)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_schedule_item, parent, false)

        return ScheduleViewHolder(layout)
    }

    override fun getItemCount(): Int = viewModel.filteredSchedulesList.size

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
//        holder.scheduleNameView.text = viewModel.filteredSchedulesList[position].uid.toString()
        holder.itemView.setOnClickListener {
            viewModel.selectedScheduleIndex = position
            it.findNavController().navigate(R.id.action_customerBookingFragment_to_customerFinishPaymentFragment)
        }
    }
}
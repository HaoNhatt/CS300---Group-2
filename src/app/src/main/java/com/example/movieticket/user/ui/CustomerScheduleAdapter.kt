package com.example.movieticket.user.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieticket.R
import com.example.movieticket.user.data.UserViewModel

class CustomerScheduleAdapter(private val viewModel: UserViewModel): RecyclerView.Adapter<CustomerScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val scheduleDateView = view.findViewById<TextView>(R.id.scheduleDate)!!
        val scheduleStartView = view.findViewById<TextView>(R.id.scheduleStart)!!
        val scheduleDurationView = view.findViewById<TextView>(R.id.scheduleDuration)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_schedule_item, parent, false)

        return ScheduleViewHolder(layout)
    }

    override fun getItemCount(): Int = viewModel.filteredSchedulesList.size

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.scheduleDateView.text = viewModel.filteredSchedulesList[position].date
        holder.scheduleStartView.text = viewModel.filteredSchedulesList[position].startTime
        holder.scheduleDurationView.text = viewModel.filteredMoviesList[viewModel.selectedMovieIndex].duration.toString()
        holder.itemView.setOnClickListener {
            viewModel.selectedScheduleIndex = position
//            viewModel.filterSeat(viewModel.filteredSchedulesList[viewModel.selectedScheduleIndex].id)
            it.findNavController().navigate(R.id.action_customerChooseScheduleFragment_to_customerChooseSeatFragment)
        }
    }
}
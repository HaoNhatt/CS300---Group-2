package com.example.movieticket.user.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieticket.R
import com.example.movieticket.user.data.UserViewModel

class CustomerSeatAdapter(private val viewModel: UserViewModel): RecyclerView.Adapter<CustomerSeatAdapter.SeatViewHolder>() {

    class SeatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val seatTypeView = view.findViewById<View>(R.id.seat)!!
//        val theaterAddressView = view.findViewById<TextView>(R.id.theaterItemAddress)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.seat_item, parent, false)

        return SeatViewHolder(layout)
    }

    override fun getItemCount(): Int = 100 // 10x10 size of seat

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
//        holder.seatTypeView.background = R.drawable.seat_free.toDrawable()
//        holder.theaterAddressView.text = viewModel.theaterList[position].address
        holder.itemView.setOnClickListener {
            viewModel.selectedSeatIndex = position
//            it.background = R.drawable.seat_choosing.toDrawable()
            holder.seatTypeView.setBackgroundResource(R.drawable.seat_choosing)
        }
    }
}
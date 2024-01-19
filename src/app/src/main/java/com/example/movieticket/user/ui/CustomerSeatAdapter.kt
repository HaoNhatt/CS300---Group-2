package com.example.movieticket.user.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieticket.R
import com.example.movieticket.user.data.SeatType
import com.example.movieticket.user.data.UserViewModel

class CustomerSeatAdapter(private val viewModel: UserViewModel, private val listener: OnSeatSelectedListener): RecyclerView.Adapter<CustomerSeatAdapter.SeatViewHolder>() {

    interface OnSeatSelectedListener {
        fun onSeatSelected()
    }

    class SeatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val seatTypeView = view.findViewById<TextView>(R.id.seat)!!
//        val theaterAddressView = view.findViewById<TextView>(R.id.theaterItemAddress)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.seat_item, parent, false)

        return SeatViewHolder(layout)
    }

    override fun getItemCount(): Int = 90 // 10x10 size of seat

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        val row = 'A' + position / 9
        val col = position % 9 + 1
        val seatName = "$row$col"
        holder.seatTypeView.text = seatName

        if (seatName in viewModel.seatSelectedList) {
            holder.seatTypeView.setBackgroundResource(R.drawable.seat_chosen)
        } else if (position >= 36 ) {
            holder.seatTypeView.setBackgroundResource(R.drawable.seat_vip)
        } else {
            holder.seatTypeView.setBackgroundResource(R.drawable.seat_free)
        }
        holder.itemView.setOnClickListener {
            viewModel.selectedSeatIndex = position

            if (seatName !in viewModel.seatSelectedList) {
                if (viewModel.seatSelectingList.size == 0) {
                    holder.seatTypeView.setBackgroundResource(R.drawable.seat_choosing)
                    viewModel.seatSelectingList.add(seatName)
                    if (position < 36) {
                        viewModel.seatType = SeatType.NORMAL
                    } else {
                        viewModel.seatType = SeatType.VIP
                    }
                    listener.onSeatSelected()
                } else if (seatName in viewModel.seatSelectingList) {
                    if (position < 36) {
                        holder.seatTypeView.setBackgroundResource(R.drawable.seat_free)
                    } else {
                        holder.seatTypeView.setBackgroundResource(R.drawable.seat_vip)
                    }
                    viewModel.seatSelectingList.remove(seatName)
                    listener.onSeatSelected()
                } else if (viewModel.seatSelectingList.size < 8) {
                    if ((viewModel.seatType == SeatType.NORMAL && position < 36) or (viewModel.seatType == SeatType.VIP && position >= 36)) {
                        holder.seatTypeView.setBackgroundResource(R.drawable.seat_choosing)
                        viewModel.seatSelectingList.add(seatName)
                        listener.onSeatSelected()
                        Log.d("HaoNhat", viewModel.seatSelectingList.toString())
                    } else {
                        val errorMessage = "You need to select seats of the same type."
                        showMaxSeatsError(holder.itemView.context, errorMessage)
                    }
                } else {
                    val errorMessage = "You can select up to 8 seats."
                    showMaxSeatsError(holder.itemView.context, errorMessage)
                }
            } else {
                val errorMessage = "Seat is not available, please choose another seat."
                showMaxSeatsError(holder.itemView.context, errorMessage)
            }
        }
    }

    private fun showMaxSeatsError(context: Context, errorMessage: String) {
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(context, errorMessage, duration)
        toast.show()
    }
}
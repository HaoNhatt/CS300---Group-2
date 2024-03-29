package com.example.movieticket.user.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieticket.R
import com.example.movieticket.user.data.UserViewModel

class CustomerBookedHistoryAdapter(private val viewModel: UserViewModel) :
    RecyclerView.Adapter<CustomerBookedHistoryAdapter.BookedTicketViewHolder>() {

    class BookedTicketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var bookedTitleTypeView = view.findViewById<TextView>(R.id.ticketMovieHistoryItemTitle)!!
        var bookedTheaterTypeView = view.findViewById<TextView>(R.id.ticketTheaterHistoryItemName)!!
        var bookedDateTimeTypeView =
            view.findViewById<TextView>(R.id.ticketDateTimeHistoryItemName)!!
        var bookedSeatsTypeView = view.findViewById<TextView>(R.id.ticketSeatsHistoryItemName)!!
        var bookedPriceTypeView = view.findViewById<TextView>(R.id.ticketPriceHistoryItem)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookedTicketViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_ticket_item, parent, false)

        return BookedTicketViewHolder(layout)
    }

    override fun getItemCount(): Int = viewModel.userTicketsList.size

    override fun onBindViewHolder(holder: BookedTicketViewHolder, position: Int) {
        holder.bookedTitleTypeView.text = viewModel.userTicketsList[position].movieTitle
        holder.bookedTheaterTypeView.text = viewModel.userTicketsList[position].theaterName
        holder.bookedDateTimeTypeView.text = viewModel.userTicketsList[position].dateTime
        holder.bookedSeatsTypeView.text = viewModel.userTicketsList[position].seatList
        "${viewModel.userTicketsList[position].price}.000 VND".also {
            holder.bookedPriceTypeView.text = it
        }

        holder.itemView.setOnClickListener {
            viewModel.selectedTicketHistory = position

        }
    }
}
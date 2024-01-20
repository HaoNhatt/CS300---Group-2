package com.example.movieticket.user.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieticket.R
import com.example.movieticket.user.data.UserViewModel

class CustomerBookedHistoryAdapter(private val viewModel: UserViewModel): RecyclerView.Adapter<CustomerBookedHistoryAdapter.BookedTicketViewHolder>() {

    class BookedTicketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var bookedTitleTypeView = view.findViewById<TextView>(R.id.ticketMovieHistoryItemTitle)!!
        var bookedTheaterTypeView = view.findViewById<TextView>(R.id.ticketTheaterHistoryItemName)!!
        var bookedDateTimeTypeView = view.findViewById<TextView>(R.id.ticketDateTimeHistoryItemName)!!
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
        var ticketTitle = ""
        var ticketTheaterName = ""
        var ticketDateTime = ""
        var movieID = ""
        var theaterID = ""

        for (schedule in viewModel.schedulesList) {
            if (schedule.id == viewModel.userTicketsList[position].scheduleID) {
                movieID = schedule.movieID
                theaterID = schedule.theaterID
                ticketDateTime = schedule.startTime + " : " + schedule.date
            }
        }
        for (theater in viewModel.theatersList) {
            if (theater.id == theaterID) {
                ticketTheaterName = theater.name
            }
        }
        for (movie in viewModel.moviesList) {
            if (movie.id == movieID) {
                ticketTitle = movie.title
            }
        }

        holder.bookedTitleTypeView.text = ticketTitle
        holder.bookedTheaterTypeView.text = ticketTheaterName
        holder.bookedDateTimeTypeView.text = ticketDateTime
        holder.bookedSeatsTypeView.text = viewModel.userTicketsList[position].seatList
        "${viewModel.userTicketsList[position].price}.000 VND".also { holder.bookedPriceTypeView.text = it }

        holder.itemView.setOnClickListener {
            viewModel.selectedTicketHistory = position

        }
    }
}
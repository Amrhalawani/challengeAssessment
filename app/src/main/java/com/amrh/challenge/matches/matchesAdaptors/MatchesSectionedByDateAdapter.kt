package com.amrh.challenge.matches.matchesAdaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrh.challenge.databinding.ItemDayBinding
import com.amrh.data.matches.pojo.Match
import javax.inject.Inject

class MatchesSectionedByDateAdapter @Inject constructor() :
    RecyclerView.Adapter<MatchesSectionedByDateAdapter.DateViewHolder>() {
    var list: ArrayList<Match> = arrayListOf()
    lateinit var listener: MatchesSectionedByDateListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        return DateViewHolder(
            ItemDayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addListener(listener: MatchesSectionedByDateListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {

        holder.bind(list[position])

    }

    fun updateList(usersList: ArrayList<Match>?) {
        if (usersList != null) {
            this.list = usersList
        }
    }

    interface MatchesSectionedByDateListener {
        fun onClicked(match: Match)
    }

    inner class DateViewHolder(binding: ItemDayBinding) : RecyclerView.ViewHolder(binding.root){
        @Inject
        lateinit var adapter: MultiHoldersFavoritesAdapter

        fun bind(match: Match) {
            adapter.updateList(list)
        //    adapter.setListener(this)

        }


    }
}

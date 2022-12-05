package com.amrh.challenge.matches.matchesAdaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrh.challenge.databinding.ItemMatchesSectionedByDayBinding
import com.amrh.data.matches.pojo.Match
import javax.inject.Inject

class MatchesSectionedByDateAdapter @Inject constructor() :
    RecyclerView.Adapter<MatchesSectionedByDateAdapter.DateViewHolder>() {
    var sectionedByDateMap: Map<String, List<Match>> = mapOf()

    lateinit var listener: MatchesSectionedByDateListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        return DateViewHolder(
            ItemMatchesSectionedByDayBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun updateMatchesMap(map: Map<String, List<Match>>) {
        this.sectionedByDateMap = map
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = sectionedByDateMap.size

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val date = sectionedByDateMap.keys.toList()[position]
        holder.bind(sectionedByDateMap[date], date)
    }

    interface MatchesSectionedByDateListener {
        fun onClicked(match: Match, isFavorite: Boolean)
    }

    inner class DateViewHolder constructor(val binding: ItemMatchesSectionedByDayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(matches: List<Match>?, date: String) {

            val adapter = MultiHoldersMatchesAdapter { view, item, position, isFavorite ->
                listener.onClicked(item, isFavorite)
            }
            binding.rvMatches.adapter = adapter
            adapter.updateList(matches)
            binding.textDay.text = date

        }
    }
}

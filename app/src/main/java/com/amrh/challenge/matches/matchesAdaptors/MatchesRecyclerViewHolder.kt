package com.amrh.challenge.matches.matchesAdaptors

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.amrh.challenge.R
import com.amrh.challenge.databinding.ItemMatchBinding
import com.amrh.challenge.databinding.ItemMatchFavoriteBinding
import com.amrh.challenge.utils.getTimeFormattedHHMM
import com.amrh.challenge.utils.visible
import com.amrh.data.matches.pojo.Match
import com.amrh.data.matches.pojo.isMatchFinished

sealed class MatchesRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    var itemClickListener: ((view: View, item: Match, position: Int) -> Unit)? = null

    class FavoriteMatchVH(private val binding: ItemMatchFavoriteBinding) : MatchesRecyclerViewHolder(binding){
        fun bind(item: Match) {
            binding.textMatchTime.text = getTimeFormattedHHMM(item.utcDate!!)
            binding.textMatchWeek.let {
                it.text = "${it.context.getString(R.string.week)} ${item.matchday}"
            }

            binding.textDate.text = getTimeFormattedHHMM(item.utcDate!!)

            binding.textTeamOne.text = item.homeTeam?.name
            binding.textTeamTwo.text = item.awayTeam?.name

            binding.imageRemoveFav.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)

            }

            //show score if exist otherwise use it to show the match time

            if (isMatchFinished(item.status!!)) {
                item.score?.fullTime?.let {
                    if (it.homeTeam != null && it.awayTeam != null) {
                        binding.textMatchState.text = "${it?.homeTeam} - ${it?.awayTeam}"
                        binding.textMatchState.visible()
                    }
                } ?: run {
                    binding.textMatchState.text = getTimeFormattedHHMM(item.utcDate!!)
                }
            } else {
                binding.textMatchState.text = getTimeFormattedHHMM(item.utcDate!!)
            }
        }
    }

    class NormalMatchVH(private val binding: ItemMatchBinding) : MatchesRecyclerViewHolder(binding){
        fun bind(item: Match) {
            binding.textMatchTime.text = getTimeFormattedHHMM(item.utcDate!!)
            binding.textMatchWeek.let {
                it.text = "${it.context.getString(R.string.week)} ${item.matchday}"
            }

            binding.textDate.text = getTimeFormattedHHMM(item.utcDate!!)

            binding.textTeamOne.text = item.homeTeam?.name
            binding.textTeamTwo.text = item.awayTeam?.name

            binding.imageFav.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)
                item.isFavorite = !item.isFavorite

            }
            //show score if exist otherwise use it to show the match time

            if (isMatchFinished(item.status!!)) {
                item.score?.fullTime?.let {
                    if (it.homeTeam != null && it.awayTeam != null) {
                        binding.textMatchState.text = "${it?.homeTeam} - ${it?.awayTeam}"
                        binding.textMatchState.visible()
                    }
                } ?: run {
                    binding.textMatchState.text = getTimeFormattedHHMM(item.utcDate!!)
                }
            } else {
                binding.textMatchState.text = getTimeFormattedHHMM(item.utcDate!!)
            }

        }
    }

}
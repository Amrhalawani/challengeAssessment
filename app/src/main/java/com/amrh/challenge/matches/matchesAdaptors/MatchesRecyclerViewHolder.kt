package com.amrh.challenge.matches.matchesAdaptors

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.amrh.challenge.R
import com.amrh.challenge.databinding.ItemMatchBinding
import com.amrh.challenge.databinding.ItemMatchFavoriteBinding
import com.amrh.challenge.utils.formatDate
import com.amrh.challenge.utils.formatTimeHHMM
import com.amrh.challenge.utils.visible
import com.amrh.data.matches.pojo.Match
import com.amrh.data.matches.pojo.isMatchFinished

sealed class MatchesRecyclerViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var itemClickListener: ((view: View, item: Match, position: Int, isFavorite: Boolean) -> Unit)? =
        null

    class FavoriteMatchVH(
        private val binding: ItemMatchFavoriteBinding,
        val multiHoldersMatchesAdapter: MultiHoldersMatchesAdapter
    ) : MatchesRecyclerViewHolder(binding) {
        fun bind(item: Match) {
            binding.textMatchTime.text = formatTimeHHMM(item.utcDate!!)
            binding.textMatchWeek.let {
                it.text = "${it.context.getString(R.string.week)} ${item.matchday}"
            }
            binding.textDate.text = formatDate(item.utcDate!!)
            binding.textTeamOne.text = item.homeTeam?.name
            binding.textTeamTwo.text = item.awayTeam?.name
            binding.textMatchState.text = item.status


            binding.imageRemoveFav.setOnClickListener {
                multiHoldersMatchesAdapter.updateMatchViewItem(item, false, adapterPosition)
                itemClickListener?.invoke(it, item, adapterPosition, false)
            }


            //show score if exist otherwise use it to show the match time
            if (isMatchFinished(item.status!!)) {
                item.score?.fullTime?.let {
                    if (it.homeTeam != null && it.awayTeam != null) {
                        binding.textMatchScore.text = "${it?.homeTeam} - ${it?.awayTeam}"
                        binding.textMatchScore.visible()
                    }
                } ?: run {
                    binding.textMatchScore.text = formatTimeHHMM(item.utcDate!!)
                }
            } else {
                binding.textMatchScore.text = formatTimeHHMM(item.utcDate!!)
            }
        }
    }

    class NormalMatchVH(
        private val binding: ItemMatchBinding,
        val multiHoldersMatchesAdapter: MultiHoldersMatchesAdapter
    ) : MatchesRecyclerViewHolder(binding) {
        fun bind(item: Match) {
            binding.textMatchTime.text = formatTimeHHMM(item.utcDate!!)
            binding.textMatchWeek.let {
                it.text = "${it.context.getString(R.string.week)} ${item.matchday}"
            }
            binding.textDate.text = formatDate(item.utcDate!!)
            binding.textTeamOne.text = item.homeTeam?.name
            binding.textTeamTwo.text = item.awayTeam?.name
            binding.textMatchState.text = item.status

            binding.imageFav.setOnClickListener {
                multiHoldersMatchesAdapter.updateMatchViewItem(item, true, adapterPosition)
                itemClickListener?.invoke(it, item, adapterPosition, true)

            }
            //show score if exist otherwise use it to show the match time

            if (isMatchFinished(item.status!!)) {
                item.score?.fullTime?.let {
                    if (it.homeTeam != null && it.awayTeam != null) {
                        binding.textMatchScore.text = "${it?.homeTeam} - ${it?.awayTeam}"
                        binding.textMatchScore.visible()
                    }
                } ?: run {
                    binding.textMatchScore.text = formatTimeHHMM(item.utcDate!!)
                }
            } else {
                binding.textMatchScore.text = formatTimeHHMM(item.utcDate!!)
            }

        }
    }

}

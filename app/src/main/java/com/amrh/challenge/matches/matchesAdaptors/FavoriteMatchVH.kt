package com.amrh.challenge.matches.matchesAdaptors

//class FavoriteMatchVH(
//    private val binding: ItemMatchFavoriteBinding,
//    val mListener: MultiHoldersFavoritesAdapter.MultiHoldersFavoritesListener
//) :
//    RecyclerView.ViewHolder(binding.root) {
//    fun bind(item: Match) {
//        binding.textMatchTime.text = getTimeFormattedHHMM(item.utcDate!!)
//        binding.textMatchWeek.let {
//            it.text = "${it.context.getString(R.string.week)} ${item.matchday}"
//        }
//
//        binding.textDate.text = getTimeFormattedHHMM(item.utcDate!!)
//
//        binding.textTeamOne.text = item.homeTeam?.name
//        binding.textTeamTwo.text = item.awayTeam?.name
//
//        binding.imageRemoveFav.setOnClickListener {
//            mListener.onFavoriteIconClicked(item.id, false)
//        }
//
//        //show score if exist otherwise use it to show the match time
//
//        if (isMatchFinished(MatchStatus.valueOf(item.status!!))) {
//            item.score?.fullTime?.let {
//                if (it.homeTeam != null && it.awayTeam != null) {
//                    binding.textMatchState.text = "${it?.homeTeam} - ${it?.awayTeam}"
//                    binding.textMatchState.visible()
//                }
//            } ?: run {
//                binding.textMatchState.text = getTimeFormattedHHMM(item.utcDate!!)
//            }
//        } else {
//            binding.textMatchState.text = getTimeFormattedHHMM(item.utcDate!!)
//        }
//    }
//
// }



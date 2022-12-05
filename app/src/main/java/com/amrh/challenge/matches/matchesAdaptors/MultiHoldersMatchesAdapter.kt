package com.amrh.challenge.matches.matchesAdaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrh.challenge.databinding.ItemMatchBinding
import com.amrh.challenge.databinding.ItemMatchFavoriteBinding
import com.amrh.data.matches.pojo.Match
import javax.inject.Inject

class MultiHoldersMatchesAdapter @Inject constructor(var itemClickListener: ((view: View, item: Match, position: Int, isFavorite:Boolean) -> Unit)? ) : RecyclerView.Adapter<MatchesRecyclerViewHolder>() {

  // lateinit var mListener: MultiHoldersFavoritesListener

    var list: List<Match>? = arrayListOf()

    fun updateList(newList: List<Match>?) {
        if (newList != null) {
            list = newList
            notifyDataSetChanged()
        }
    }

    fun updateMatchItem(match: Match, isFavorite:Boolean, position: Int) {
        match.isFavorite = isFavorite
        notifyItemChanged(position)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (list?.get(position)?.isFavorite) {
            true -> MatchViewType.FAVORITE_MATCH_VIEW_TYPE.viewTypeId
            else -> {
                MatchViewType.NORMAL_UNSELECTED_MATCH_VIEW_TYPE.viewTypeId
            }
        }
    }

    override fun onBindViewHolder(holder: MatchesRecyclerViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener
        when (holder.itemViewType) {
            MatchViewType.NORMAL_UNSELECTED_MATCH_VIEW_TYPE.viewTypeId -> {
                val u: MatchesRecyclerViewHolder.NormalMatchVH = holder as MatchesRecyclerViewHolder.NormalMatchVH
                u.bind(list?.get(position)!!)
            }
            MatchViewType.FAVORITE_MATCH_VIEW_TYPE.viewTypeId -> {
                val s: MatchesRecyclerViewHolder.FavoriteMatchVH = holder as MatchesRecyclerViewHolder.FavoriteMatchVH
                s.bind(list?.get(position)!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesRecyclerViewHolder {
        return when (viewType) {
            MatchViewType.NORMAL_UNSELECTED_MATCH_VIEW_TYPE.viewTypeId -> {
                MatchesRecyclerViewHolder.NormalMatchVH( ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false), this)
            }
            else -> {
                MatchesRecyclerViewHolder.FavoriteMatchVH( ItemMatchFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false),this)
            }
        }
    }

    override fun getItemCount(): Int = list?.size!!

}


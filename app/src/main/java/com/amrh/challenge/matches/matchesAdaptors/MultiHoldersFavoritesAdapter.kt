package com.amrh.challenge.matches.matchesAdaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrh.challenge.databinding.ItemMatchBinding
import com.amrh.challenge.databinding.ItemMatchFavoriteBinding
import com.amrh.data.matches.pojo.Match
import javax.inject.Inject

class MultiHoldersFavoritesAdapter @Inject constructor( var itemClickListener: ((view: View, item: Match, position: Int) -> Unit)? ) : RecyclerView.Adapter<MatchesRecyclerViewHolder>() {
    private var list: ArrayList<Match>? = arrayListOf()
   // lateinit var mListener: MultiHoldersFavoritesListener
 //  var itemClickListener: ((view: View, item: Match, position: Int) -> Unit)? = null

    fun updateList(newList: ArrayList<Match>?) {
        if (newList != null) {
            this.list = newList
            notifyDataSetChanged()
        }
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
                MatchesRecyclerViewHolder.NormalMatchVH( ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> {
                MatchesRecyclerViewHolder.FavoriteMatchVH( ItemMatchFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun getItemCount(): Int = list?.size!!



}


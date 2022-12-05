package com.amrh.data.matches

import com.amrh.data.matches.local.LocalMatchesDataSource
import com.amrh.data.matches.pojo.Match
import com.amrh.data.matches.pojo.MatchesRes
import com.amrh.data.matches.remote.RemoteMatchesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MatchesRepository @Inject constructor(
    private val localMatchesDS: LocalMatchesDataSource,
    private val remoteMatchesDS: RemoteMatchesDataSource
) {


    suspend fun getFavoritesMatches(): Flow<List<Match>> {
        return localMatchesDS.fetchFavoritesMatches()
    }

    suspend fun getMatches(): MatchesRes {
        return remoteMatchesDS.fetchMatches()
    }

    suspend fun addFavoriteMatch(match: Match) {
        return localMatchesDS.addFavoriteMatch(match)
    }

    suspend fun removeFavoriteMatch(match: Match) {
        return localMatchesDS.removeFavoriteMatch(match)
    }

}
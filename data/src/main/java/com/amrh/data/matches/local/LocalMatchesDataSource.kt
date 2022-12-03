package com.amrh.data.matches.local

import com.amrh.data.matches.local.db.FavoriteMatchesDao
import com.amrh.data.matches.pojo.Match
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMatchesDataSource @Inject constructor(private val favoriteMatchesDao: FavoriteMatchesDao) {

    suspend fun fetchFavoritesMatches(): Flow<List<Match>> {
        return favoriteMatchesDao.selectAllFavoriteMatchesList()
    }

    suspend fun addFavoriteMatch(match: Match) {
        favoriteMatchesDao.insertMatch(match)
    }

    suspend fun removeFavoriteMatch(match: Match) {
        favoriteMatchesDao.deleteFavoriteMatch(match)
    }
}
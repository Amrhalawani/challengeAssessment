package com.amrh.data.matches.local

import com.amrh.data.matches.local.db.FavoriteMatchesDao
import com.amrh.data.matches.pojo.Match
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMatchesDataSource @Inject constructor(private val favoriteMatchesDao: FavoriteMatchesDao) {

     fun fetchFavoritesMatches(): Flow<List<Match>> {
        return favoriteMatchesDao.selectAllFavoriteMatchesList()
    }

    fun fetchFavoriteMatchIDs(): Flow<List<Int>> {
        return favoriteMatchesDao.selectFavoriteMatchIDs()
    }

     fun addFavoriteMatch(match: Match) {
        favoriteMatchesDao.insertMatch(match)
    }

     fun removeFavoriteMatch(match: Match){
        favoriteMatchesDao.deleteFavoriteMatch(match)
    }
}
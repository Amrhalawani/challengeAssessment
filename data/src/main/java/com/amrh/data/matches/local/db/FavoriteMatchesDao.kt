package com.amrh.data.matches.local.db

import androidx.room.*
import com.amrh.data.matches.pojo.Match
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMatchesDao {

    @Query("SELECT * FROM `Match` ")
    fun selectAllFavoriteMatchesList(): Flow<List<Match>>

    @Query("SELECT * FROM `Match` where id=:matchId")
    fun selectFavoriteMatch(matchId: String): Flow<Match>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(match: Match)

    @Delete
    fun deleteFavoriteMatch(match: Match)

    @Query("DELETE From `Match` where id=:matchId")
    fun deleteFavoriteMatchById(matchId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchesList(matches: List<Match>)


}
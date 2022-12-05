package com.amrh.data.matches

import com.amrh.challenge.utils.getDateFormattedYYYYMMDD
import com.amrh.data.matches.pojo.GroupedMatches
import com.amrh.data.matches.pojo.Match
import com.amrh.data.matches.remote.Result
import com.amrh.data.matches.remote.flowAsResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MatchesUseCases @Inject constructor(
    val repository: MatchesRepository
) {
    suspend fun getMatchesGroupedByDate() =
        flow { emit(repository.getMatches()) }.flowAsResult().map {
            when (it) {
                is Result.Success -> {
                    Result.Success(GroupedMatches(value = convertToGroupedMatchesByDate(it.data.matches)))
                }
                is Result.Loading -> {
                    Result.Loading
                }
                is Result.Failure -> {
                    Result.Failure(it.exception)
                }
                else -> {
                    Result.Loading
                }
            }
        }

    suspend fun getFavoriteMatches() = flow {
            repository.getFavoritesMatches().map {
                emit(convertToGroupedMatchesByDate(it))
            }
        }


    suspend fun addFavoriteMatch(match: Match) {
        return repository.addFavoriteMatch(match)
    }

    suspend fun removeFavoriteMatch(match: Match) {
        return repository.removeFavoriteMatch(match)
    }

    fun convertToGroupedMatchesByDate(matches: List<Match>): Map<String, List<Match>> {
        return matches.groupBy { getDateFormattedYYYYMMDD(it?.utcDate!!) }
    }

}


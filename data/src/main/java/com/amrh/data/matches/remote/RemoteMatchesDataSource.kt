package com.amrh.data.matches.remote

import com.amrh.data.matches.pojo.MatchesRes
import kotlinx.coroutines.flow.Flow

class RemoteMatchesDataSource(private val apiClient: ApiClient) {

    suspend fun fetchMatches(): Flow<MatchesRes> {
        return apiClient.getMatches()
    }
}
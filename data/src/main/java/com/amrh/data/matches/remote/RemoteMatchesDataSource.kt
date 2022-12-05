package com.amrh.data.matches.remote

import com.amrh.data.matches.pojo.MatchesRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteMatchesDataSource @Inject constructor(private val apiClient: ApiClient) {
    suspend fun fetchMatches(): MatchesRes {
        return apiClient.getMatches()
    }
}
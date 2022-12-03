package com.amrh.data.matches.remote

import com.amrh.data.matches.pojo.MatchesRes
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface ApiClient {

    @GET(MATCHES_2021_END_POINT)
    suspend fun getMatches(): Flow<MatchesRes>

}
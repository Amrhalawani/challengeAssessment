package com.amrh.data.matches

import com.amrh.data.matches.local.LocalMatchesDataSource
import com.amrh.data.matches.remote.RemoteMatchesDataSource
import javax.inject.Inject

class MatchesRepository @Inject constructor(
    val localMatchesDS: LocalMatchesDataSource,
    val remoteMatchesDS: RemoteMatchesDataSource
) {



}
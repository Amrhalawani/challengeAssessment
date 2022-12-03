package com.amrh.data.matches

import com.amrh.data.matches.local.LocalMatchesDataSource
import com.amrh.data.matches.remote.RemoteMatchesDataSource

class MatchesRepository(val localMatchesDS: LocalMatchesDataSource, val remoteMatchesDS: RemoteMatchesDataSource) {


}
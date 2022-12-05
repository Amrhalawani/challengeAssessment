package com.amrh.data.matches.pojo


 fun FakeMatchResource(
    id: Int,
    date: String,
) = Match(
    matchday = 1,
    awayTeam = null,
    utcDate = date,
    lastUpdated = null,
    score = null,
    stage = null,
    season = null,
    homeTeam = null,
    id = id,
    referees = listOf(),
    status = null
)

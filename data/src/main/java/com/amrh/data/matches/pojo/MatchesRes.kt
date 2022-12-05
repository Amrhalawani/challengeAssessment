package com.amrh.data.matches.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MatchesRes(

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("competition")
    val competition: Competition? = null,

    @field:SerializedName("matches")
    var matches: List<Match> = listOf(),

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("errorCode")
    val errorCode: String? = null,
)


@Entity
data class Team(

    @field:SerializedName("name")
    val name: String? = null,

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = null
)


data class GroupedMatches(
    val value: Map<String, List<Match>> = mapOf(),
)


@Entity
data class Match(

    @PrimaryKey
    @field:SerializedName("id")
    var id: Int,

    
    @field:SerializedName("matchday")
    val matchday: Int? = null,

    @field:SerializedName("awayTeam")
    val awayTeam: Team? = null,

    @field:SerializedName("utcDate")
    val utcDate: String? = null,

    @field:SerializedName("lastUpdated")
    val lastUpdated: String? = null,

    @field:SerializedName("score")
    val score: Score? = null,

    @field:SerializedName("stage")
    val stage: String? = null,

    @field:SerializedName("season")
    val season: Season? = null,

    @field:SerializedName("homeTeam")
    val homeTeam: Team? = null,



    @field:SerializedName("referees")
    val referees: List<RefereesItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null,

    var isFavorite: Boolean = false,

    )


@Entity
data class Penalties(

    @field:SerializedName("awayTeam")
    val awayTeam: Int? = null,

    @field:SerializedName("homeTeam")
    val homeTeam: Int? = null
)

@Entity
data class Score(

    @field:SerializedName("duration")
    val duration: String? = null,

    @field:SerializedName("winner")
    val winner: String? = null,

    @field:SerializedName("penalties")
    val penalties: Penalties? = null,

    @field:SerializedName("halfTime")
    val halfTime: PeriodOfTime? = null,

    @field:SerializedName("fullTime")
    val fullTime: PeriodOfTime? = null,

    @field:SerializedName("extraTime")
    val extraTime: PeriodOfTime? = null
)

@Entity
data class PeriodOfTime(

    @field:SerializedName("awayTeam")
    val awayTeam: Int? = null,

    @field:SerializedName("homeTeam")
    val homeTeam: Int? = null
)

@Entity
data class Season(

    @field:SerializedName("currentMatchday")
    val currentMatchday: Int? = null,

    @field:SerializedName("endDate")
    val endDate: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("startDate")
    val startDate: String? = null
)

@Entity
data class RefereesItem(

    @field:SerializedName("role")
    val role: String? = null,

    @field:SerializedName("nationality")
    val nationality: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)

@Entity
data class Area(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)

@Entity
data class Odds(

    @field:SerializedName("msg")
    val msg: String? = null
)

@Entity
data class Competition(

    @field:SerializedName("area")
    val area: Area? = null,

    @field:SerializedName("lastUpdated")
    val lastUpdated: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("plan")
    val plan: String? = null
)

data class Filters(
    val any: Any? = null
)

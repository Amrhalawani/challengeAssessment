package com.amrh.data.matches.local.db

import androidx.room.TypeConverter
import com.amrh.data.matches.pojo.RefereesItem
import com.amrh.data.matches.pojo.Score
import com.amrh.data.matches.pojo.Season
import com.amrh.data.matches.pojo.Team
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object Converters {


    @TypeConverter
    @JvmStatic
    fun teamToString(team: Team?): String? {
        if (team == null) {
            return null
        }
        return Gson().toJson(team)
    }

    @TypeConverter
    @JvmStatic
    fun stringToTeam(value: String?): Team? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<Team?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun scoreToString(score: Score?): String? {
        score?.let {
            return Gson().toJson(it)
        }
        return null
    }

    @TypeConverter
    @JvmStatic
    fun stringToScore(value: String?): Score? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<Score?>() {}.type
        return Gson().fromJson(value, type)
    }


    @TypeConverter
    @JvmStatic
    fun seasonToString(season: Season?): String? {
        season?.let {
            return Gson().toJson(it)
        }
        return null
    }

    @TypeConverter
    @JvmStatic
    fun stringToSeason(value: String?): Season? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<Season?>() {}.type
        return Gson().fromJson(value, type)
    }


    @TypeConverter
    @JvmStatic
    fun refereesToString(list: List<RefereesItem?>??): String? {
        list?.let {
            return Gson().toJson(it)
        }
        return null
    }

    @TypeConverter
    @JvmStatic
    fun stringToReferees(value: String?): List<RefereesItem?>? {
        if (value == null) {
            return null
        }
        val type: Type = object : TypeToken<List<RefereesItem?>?>() {}.type
        return Gson().fromJson(value, type)
    }
}
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


//    @TypeConverter // note this annotation
//    fun fromOptionValuesList(optionValues: List<PlayerPositionRecord?>?): String? {
//        if (optionValues == null) {
//            return null
//        }
//        val type: Type = object : TypeToken<List<PlayerPositionRecord?>?>() {}.type
//        return Gson().toJson(optionValues, type)
//    }
//
//    @TypeConverter // note this annotation
//    fun toOptionValuesList(optionValuesString: String?): List<PlayerPositionRecord?>? {
//        if (optionValuesString == null) {
//            return null
//        }
//        val type: Type = object : TypeToken<List<PlayerPositionRecord?>?>() {}.type
//        return Gson().fromJson(optionValuesString, type)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun listToString(value: List<String>?): String? {
//        if (value == null) {
//            return null
//        }
//        return Gson().toJson(value)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun stringToList(value: String?): List<String>? {
//        if (value == null) {
//            return null
//        }
//        val type: Type = object : TypeToken<List<String?>?>() {}.type
//        return Gson().fromJson(value, type)
//    }
//
//
//    @TypeConverter
//    @JvmStatic
//    fun botConfigToString(value: List<BotConfig>?): String? {
//        if (value == null) {
//            return null
//        }
//        return Gson().toJson(value)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun stringToBotConfig(value: String?): List<BotConfig>? {
//        if (value == null) {
//            return null
//        }
//        val type: Type = object : TypeToken<List<BotConfig?>?>() {}.type
//        return Gson().fromJson(value, type)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun categoryDetailsToString(value: CategoryDetails?): String? {
//        if (value == null) {
//            return null
//        }
//        return Gson().toJson(value)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun stringToCategoryDetails(value: String?): CategoryDetails? {
//        if (value == null) {
//            return null
//        }
//        val type: Type = object : TypeToken<CategoryDetails?>() {}.type
//        return Gson().fromJson(value, type)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun fromGroupTaskMemberList(value: List<CategoryDetails>): String {
//        val type = object : TypeToken<List<CategoryDetails>>() {}.type
//        return Gson().toJson(value, type)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun toGroupTaskMemberList(value: String): List<CategoryDetails> {
//        val type = object : TypeToken<List<CategoryDetails>>() {}.type
//        return Gson().fromJson(value, type)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun battleIndiaToString(value: List<BattleIndia>?): String? {
//        if (value == null) {
//            return null
//        }
//        return Gson().toJson(value)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun stringToBattleIndia(value: String?): List<BattleIndia>? {
//        if (value == null) {
//            return null
//        }
//        val type: Type = object : TypeToken<List<BattleIndia?>?>() {}.type
//        return Gson().fromJson(value, type)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun tournamentIndiaToString(value: List<TournamentIndia>?): String? {
//        if (value == null) {
//            return null
//        }
//        return Gson().toJson(value)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun stringToTournamentIndia(value: String?): List<TournamentIndia>? {
//        if (value == null) {
//            return null
//        }
//        val type: Type = object : TypeToken<List<TournamentIndia?>?>() {}.type
//        return Gson().fromJson(value, type)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun prizesDistributionToString(value: List<PrizesDistribution>?): String? {
//        if (value == null) {
//            return null
//        }
//        return Gson().toJson(value)
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun stringToPrizesDistribution(value: String?): List<PrizesDistribution>? {
//        if (value == null) {
//            return null
//        }
//        val type: Type = object : TypeToken<List<PrizesDistribution?>?>() {}.type
//        return Gson().fromJson(value, type)
//    }
//

}
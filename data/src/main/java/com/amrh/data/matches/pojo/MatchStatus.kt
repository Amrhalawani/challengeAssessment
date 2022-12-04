package com.amrh.data.matches.pojo


enum class MatchStatus(val status: String) {
    SCHEDULED("SCHEDULED"),
    FINISHED("FINISHED"),
    POSTPONED("POSTPONED"),
}

fun isMatchFinished(matchStatus: String): Boolean {
    return try {
        when (MatchStatus.valueOf(matchStatus)) {
            MatchStatus.FINISHED -> true
            else -> false
        }
    } catch (e: Exception) {
        false
    }
}

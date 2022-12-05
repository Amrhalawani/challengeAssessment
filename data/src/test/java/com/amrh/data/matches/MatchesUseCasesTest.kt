package com.amrh.data.matches

import com.amrh.challenge.utils.getDateFormattedYYYYMMDD
import com.amrh.data.matches.pojo.FakeMatchResource
import com.amrh.data.matches.pojo.Match
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class MatchesUseCasesTest {


//    @Inject
//    private lateinit var matchesUseCases: MatchesUseCases

    @Before
    fun createMatchesUseCases() {
      //  matchesUseCases = MatchesUseCases(null)
    }


    @Test
    fun get_grouped_matches_by_date_as_a_key_converted_from_list() {

        val matchesList: ArrayList<Match> = arrayListOf()

        //fill 3 matches for 3 days in sequence, 1 july, 2 july, 3 july
        for (i in 1..3) {
            matchesList.add(FakeMatchResource(i, "2022-07-0${i}T00:00:00Z"))
        }

        //also fill 2 matches for 2 days in sequence, 2 july, 3 july

        matchesList.add(FakeMatchResource(4, "2022-07-02T00:00:00Z"))

        // so for now we have list contains  ( 1 match for 1 july, and 3 july) , (2 matches for 2 july)


        //get Size Of THe list of 2 july matches , it must be 2
//        val actual = matchesUseCases.convertToGroupedMatchesByDate(matchesList)[getDateFormattedYYYYMMDD("2022-07-02T00:00:00Z")]?.size
//        val expected = 2
//
//        assertEquals(expected, actual)
    }
}
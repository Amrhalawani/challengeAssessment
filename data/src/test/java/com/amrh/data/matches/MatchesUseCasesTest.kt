package com.amrh.data.matches

import com.amrh.challenge.utils.formatDate
import com.amrh.data.matches.pojo.FakeMatchResource
import com.amrh.data.matches.pojo.Match
import com.amrh.data.matches.repo.FakeMatchesRepository
import com.amrh.data.matches.repo.MatchesUseCases
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class MatchesUseCasesTest {


    val matches: ArrayList<Match> = arrayListOf()

    lateinit var matchesUseCases: MatchesUseCases

    val firstJuly = "2022-07-01T00:00:00Z"
    val secondJuly = "2022-07-02T00:00:00Z"
    val thirdJuly = "2022-07-03T00:00:00Z"


    @Before
    fun createMatchesUseCases() {
        matchesUseCases = MatchesUseCases(FakeMatchesRepository())
    }

    @Before
    fun setupOurFakeList(){
        //fill 3 matches for 3 days in sequence, 1 july, 2 july, 3 july
        for (i in 1..3) {
            matches.add(FakeMatchResource(i, "2022-07-0${i}T00:00:00Z"))
        }
        //also fill 2 matches for 2 days in sequence, 2 july, 3 july
        matches.add(FakeMatchResource(4, secondJuly))
        // so for now we have list contains  1 match for (1 july, and 3 july) , 2 matches for (2 july)
    }


    @Test
    fun get_grouped_matches_by_date_as_a_key_converted_from_list() {

        // get Size Of THe list of 2 july matches , it must be 2
        val actual =
            matchesUseCases.convertToGroupedMatchesByDate(matches)[formatDate(
                secondJuly
            )]?.size

        val expected = 2

        assertEquals(expected, actual)

    }

    @Test
    fun given_the_saved_fav_ids_convert_matches_to_fav_if_its_id_is_included____Case_all_are_favorites() {
        val groupedMatches = matchesUseCases.convertToGroupedMatchesByDate(matches)
        val fakeFavoritesIds = matches.map { it.id }
        //all the list should be converted to true
        val actual = matchesUseCases.convertMatchesFavStatesDependsOnIds (fakeFavoritesIds as MutableList<Int>, groupedMatches)

        //map size should be 3
        assertEquals(3, actual.size)

        assertEquals(true, actual[formatDate(firstJuly)]?.get(0)?.isFavorite)
        assertEquals(true, actual[formatDate(secondJuly)]?.get(0)?.isFavorite)
        assertEquals(true, actual[formatDate(secondJuly)]?.get(1)?.isFavorite)
        assertEquals(true, actual[formatDate(thirdJuly)]?.get(0)?.isFavorite)

    }


    @Test
    fun given_the_saved_ids_convert_all_fav_status_if_exist____Case_id_1_only_favorite() {

        val groupedMatches = matchesUseCases.convertToGroupedMatchesByDate(matches)

        val fakeIds:List<Int> = listOf(1)

        //all the list must be converted to true
        val actual = matchesUseCases.convertMatchesFavStatesDependsOnIds(fakeIds as MutableList<Int>, groupedMatches)

        //map size should be 3
        assertEquals(3, actual.size)

        assertEquals(true, actual[formatDate(firstJuly)]?.get(0)?.isFavorite)
        assertEquals(false, actual[formatDate(secondJuly)]?.get(0)?.isFavorite)
        assertEquals(false, actual[formatDate(secondJuly)]?.get(1)?.isFavorite)
        assertEquals(false, actual[formatDate(thirdJuly)]?.get(0)?.isFavorite)

    }


    @Test
    fun given_the_saved_ids_convert_all_fav_status_if_exist____Case_all_are_favorite_exept_id_1() {

        val groupedMatches = matchesUseCases.convertToGroupedMatchesByDate(matches)

        val fakeIds:List<Int> = listOf(2,3,4)

        print(fakeIds)
        //all the list must be converted to true
        val actual = matchesUseCases.convertMatchesFavStatesDependsOnIds(fakeIds as MutableList<Int>, groupedMatches)

        //map size should be 3
        assertEquals(3, actual.size)

        assertEquals(false, actual[formatDate(firstJuly)]?.get(0)?.isFavorite)
        assertEquals(true, actual[formatDate(secondJuly)]?.get(0)?.isFavorite)
        assertEquals(true, actual[formatDate(secondJuly)]?.get(1)?.isFavorite)
        assertEquals(true, actual[formatDate(thirdJuly)]?.get(0)?.isFavorite)

    }


}
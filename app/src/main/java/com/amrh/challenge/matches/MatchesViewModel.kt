/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amrh.challenge.matches


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrh.data.matches.pojo.GroupedMatches
import com.amrh.data.matches.pojo.Match
import com.amrh.data.matches.remote.Result
import com.amrh.data.matches.repo.MatchesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchesUseCase: MatchesUseCases
) : ViewModel() {

    private val _stateMatches: MutableLiveData<Result<GroupedMatches>> = MutableLiveData()

    val stateMatches: LiveData<Result<GroupedMatches>> = _stateMatches


    private val _stateFavoritesMatchesIds: MutableLiveData<List<Int>> = MutableLiveData()

    val stateFavoritesMatchesIds: LiveData<List<Int>> = _stateFavoritesMatchesIds

    init {
        getMatches()
        getFavoriteMatchesIds()
    }

    private fun getMatches() {
        viewModelScope.launch {
            matchesUseCase.getMatchesGroupedByDate().collectLatest {
                when (it) {
                    is Result.Success -> {
                        _stateMatches.postValue(Result.Success(it.data))
                    }
                    is Result.Loading -> {
                        _stateMatches.postValue(Result.Loading)
                    }
                    is Result.Failure -> {
                        _stateMatches.postValue(Result.Failure(it.exception))
                    }
                    else -> {
                        _stateMatches.postValue(Result.Loading)
                    }
                }
            }
        }

    }

    fun addFavoriteMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            matchesUseCase.addFavoriteMatch(match)
        }
    }

    private fun getFavoriteMatches() {
        viewModelScope.launch(Dispatchers.IO) {
            matchesUseCase.getFavoriteMatches()
        }
    }


    fun getFavoriteMatchesIds() {
        viewModelScope.launch(Dispatchers.IO) {
            matchesUseCase.getFavoriteMatchesIds().collectLatest {
                _stateFavoritesMatchesIds.postValue(it)
            }
        }
    }

     fun convertMatchesFavStatesDependsOnIds(
        favoritesIds: MutableList<Int>,
        sectionedByDateMap: Map<String, List<Match>>
    ): Map<String, List<Match>> {
        return matchesUseCase.convertMatchesFavStatesDependsOnIds(favoritesIds, sectionedByDateMap)
    }

    fun removeFavoriteMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            matchesUseCase.removeFavoriteMatch(match)
        }
    }


}


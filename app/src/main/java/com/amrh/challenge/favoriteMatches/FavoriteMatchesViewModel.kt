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

package com.amrh.challenge.favoriteMatches


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amrh.data.matches.MatchesUseCases
import com.amrh.data.matches.pojo.Match
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMatchesViewModel @Inject constructor(
    private val matchesUseCase: MatchesUseCases
) : ViewModel() {

    //mutable able to change
    private val _stateFavoritesMatches: MutableLiveData<Map<String, List<Match>>> = MutableLiveData()

    //just for observing
    val stateFavoritesMatches: LiveData<Map<String, List<Match>>> = _stateFavoritesMatches

    init {
        getFavoriteMatches()
    }
    private fun getFavoriteMatches() {
        viewModelScope.launch(Dispatchers.IO) {
            matchesUseCase.getFavoriteMatches().collectLatest {
                _stateFavoritesMatches.postValue(it)
            }
        }
    }

    private fun removeFavoriteMatch(match: Match) {
        viewModelScope.launch (Dispatchers.IO){
            matchesUseCase.removeFavoriteMatch(match)
        }
    }
}


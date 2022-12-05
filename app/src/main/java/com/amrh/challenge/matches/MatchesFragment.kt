package com.amrh.challenge.matches

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.amrh.challenge.databinding.FragmentMatchesBinding
import com.amrh.challenge.matches.matchesAdaptors.MatchesSectionedByDateAdapter
import com.amrh.challenge.utils.*
import com.amrh.data.matches.pojo.Match
import com.amrh.data.matches.remote.Result
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MatchesFragment : Fragment(), MatchesSectionedByDateAdapter.MatchesSectionedByDateListener {

    val viewModel: MatchesViewModel by viewModels()

    @Inject
    lateinit var adapter: MatchesSectionedByDateAdapter

    private var _binding: FragmentMatchesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeMatches()
        observeFavoritesMatchesIDs()
        setupAdaptor()
        listeners()
    }


    override fun onResume() {
        super.onResume()

        actionAfter(1000) { sendIntentFavIds() }

    }

    private fun setupAdaptor() {
        binding.rvAllMatches.adapter = adapter
        adapter.listener = this
    }

    private fun listeners() {
        binding.include.llShowFav.setOnClickListener {
            findNavController().navigate(MatchesFragmentDirections.actionNavigationMatchesToFavoritesMatchesFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun observeMatches() {
        showProgress(true)
        viewModel.stateMatches.observe(viewLifecycleOwner) { result ->
            showProgress(false)
            when (result) {
                is Result.Success -> {

                    adapter.updateMatchesMap(result.data.value)

                    scrollToCurrentDate(result.data.value)
                }
                is Result.Loading -> {
                    showProgress(true)
                }
                is Result.Failure -> {
                    context?.showToast(result.exception?.localizedMessage.toString())
                }
            }
        }
    }

    private fun scrollToCurrentDate(value: Map<String, List<Match>>): Int {
        val currentDate= getCurrentDateUnixTime()
        var position = 0
        value.values.flatten().map {
            formatToUnixTimeBackEndFormation(it.utcDate!!)
        }.forEachIndexed{ index, date ->
            if (date!! >= currentDate!!){
                position = index+1
                return position
            }
        }
        return position
        //  binding.rvAllMatches?.scrollToPosition(position)
    }

    private fun observeFavoritesMatchesIDs() {
        showProgress(true)
        viewModel.stateFavoritesMatchesIds.observe(viewLifecycleOwner) { result ->
            showProgress(false)
            adapter.updateMatchesMap(
                viewModel.convertMatchesFavStatesDependsOnIds(
                    result as MutableList<Int>,
                    adapter.sectionedByDateMap
                )
            )
            Log.e("TAG", "observeFavoritesMatchesIDs: ${result.toString()}")
        }
    }

    private fun sendIntentFavIds() {
        viewModel.getFavoriteMatchesIds()
    }

    private fun addFavoriteMatch(match: Match) {
        viewModel.addFavoriteMatch(match)
    }

    private fun removeFavoriteMatch(match: Match) {
        viewModel.removeFavoriteMatch(match)
    }


    private fun showProgress(b: Boolean) {
        if (b) {
            binding.animationView.visible()
            binding.animationView.playAnimation()
        } else {
            binding.animationView.gone()
            binding.animationView.cancelAnimation()
        }
    }

    override fun onClicked(match: Match, isFavorite: Boolean) {
        if (isFavorite) {
            addFavoriteMatch(match)
        } else {
            removeFavoriteMatch(match)
        }
    }
}
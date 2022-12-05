package com.amrh.challenge.favoriteMatches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amrh.challenge.R
import com.amrh.challenge.databinding.FragmentFavoritesMatchesBinding
import com.amrh.challenge.matches.matchesAdaptors.MatchesSectionedByDateAdapter
import com.amrh.challenge.utils.gone
import com.amrh.challenge.utils.showToast
import com.amrh.challenge.utils.visible
import com.amrh.data.matches.pojo.Match
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesMatchesFragment : Fragment(), MatchesSectionedByDateAdapter.MatchesSectionedByDateListener {

    val viewModel: FavoriteMatchesViewModel by viewModels()

    @Inject
    lateinit var adapter: MatchesSectionedByDateAdapter

    private var _binding: FragmentFavoritesMatchesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesMatchesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillUi()
        setupFavoriteAdapter()
        observeFavoriteMatches()
        listeners()
    }

    private fun setupFavoriteAdapter() {
        binding.rvAllMatches.adapter = adapter
    }

    private fun observeFavoriteMatches() {

        viewModel.stateFavoritesMatches.observe(viewLifecycleOwner) { result ->
            context?.showToast(result.size.toString())
            if (result.size>0){
                adapter.updateMatchesMap(result)
                binding.textEmpty.gone()
            } else {
                binding.textEmpty.visible()
            }
    }}

    private fun listeners() {
        binding.include.llShowFav.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun fillUi() {

        binding.include?.textShowFav?.text = getString(R.string.show_all)
        binding.include?.imageShowFavorites?.setImageResource(R.drawable.ic_view_all)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClicked(match: Match, isFavorite: Boolean) {

    }


}
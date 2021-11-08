package com.kohlwage.boschkraken.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.kohlwage.boschkraken.R
import com.kohlwage.boschkraken.databinding.FragmentAssetDetailsBinding
import com.kohlwage.boschkraken.ui.list.TradingAssetListFragment.Companion.ASSET_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TradingAssetDetailsFragment : Fragment() {

    private val detailViewModel: TradingAssetDetailsViewModel by viewModels()

    private var _binding: FragmentAssetDetailsBinding? = null
    private val binding: FragmentAssetDetailsBinding
        get() = _binding!!

    private lateinit var job: Job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAssetDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadAssetDetails()
        initObservers()
    }

    private fun initViews() {
        binding.viewmodel = detailViewModel
        binding.swipeRefresh.setOnRefreshListener {
            job.cancel()
            loadAssetDetails()
        }
        binding.error.setOnClickListener { loadAssetDetails() }
        binding.close.setOnClickListener {
            Navigation.findNavController(binding.close).popBackStack()
        }
    }

    private fun loadAssetDetails() {
        val movie = arguments?.getString(ASSET_ID) ?: ""
        detailViewModel.assetId = movie
        job = lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.getDetailFlow().collectLatest {
                    binding.details = it
                }
            }
        }
    }

    private fun initObservers() {
        detailViewModel.error.observe(viewLifecycleOwner, { isError ->
            binding.error.visibility = if (isError) View.VISIBLE else View.GONE
            binding.swipeRefresh.visibility = if (isError) View.GONE else View.VISIBLE
        })
        detailViewModel.refreshing.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = it
        })
        detailViewModel.lastUpdate.observe(viewLifecycleOwner, {
            binding.lastUpdateTime.text = getString(R.string.last_updated, it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
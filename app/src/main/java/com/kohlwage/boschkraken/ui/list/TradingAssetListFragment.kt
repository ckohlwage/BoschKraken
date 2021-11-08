package com.kohlwage.boschkraken.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kohlwage.boschkraken.R
import com.kohlwage.boschkraken.databinding.FragmentAssetListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TradingAssetListFragment : Fragment(R.layout.fragment_asset_list) {

    @Inject
    lateinit var tradingAssetAdapter: TradingAssetListAdapter

    private val listAssetListViewModel: TradingAssetListViewModel by activityViewModels()

    private var _binding: FragmentAssetListBinding? = null
    private val binding: FragmentAssetListBinding
        get() = _binding!!

    private lateinit var job: Job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAssetListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
        initViews()
        collectList()
    }

    private fun initViews() {
        binding.assetRecycler.adapter = tradingAssetAdapter
        binding.swipeRefresh.setOnRefreshListener {
            job.cancel()
            collectList()
        }
    }

    private fun collectList() {
        job = lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                listAssetListViewModel.getTradingListFlow().collect {
                    tradingAssetAdapter.submitList(it)
                }
            }
        }
    }

    private fun registerObservers() {
        listAssetListViewModel.error.observe(viewLifecycleOwner, { isError ->
            binding.error.visibility = if (isError) View.VISIBLE else View.GONE
            binding.swipeRefresh.visibility = if (isError) View.GONE else View.VISIBLE
        })
        listAssetListViewModel.refreshing.observe(viewLifecycleOwner, Observer {
            binding.swipeRefresh.isRefreshing = it
        })
        listAssetListViewModel.lastUpdate.observe(viewLifecycleOwner, Observer {
            binding.lastUpdateTime.text = getString(R.string.last_updated, it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ASSET_ID = "asset_id"
    }

}
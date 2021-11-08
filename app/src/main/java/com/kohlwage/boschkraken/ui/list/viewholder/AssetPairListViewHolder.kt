package com.kohlwage.boschkraken.ui.list.viewholder

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kohlwage.boschkraken.R
import com.kohlwage.boschkraken.databinding.AssetListItemBinding
import com.kohlwage.boschkraken.models.TradingOverviewItem
import com.kohlwage.boschkraken.ui.list.TradingAssetListFragment

class AssetPairListViewHolder(private val binding: AssetListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: TradingOverviewItem) {
        binding.overviewItem = item
        binding.root.setOnClickListener {
            val bundle = Bundle(2).apply {
                this.putString(TradingAssetListFragment.ASSET_ID, item.name)
            }
            Navigation.findNavController(binding.root).navigate(R.id.movieDetailFragment, bundle)
        }
    }
}

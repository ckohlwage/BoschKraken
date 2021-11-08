package com.kohlwage.boschkraken.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kohlwage.boschkraken.databinding.AssetListItemBinding
import com.kohlwage.boschkraken.models.TradingOverviewItem
import com.kohlwage.boschkraken.ui.list.viewholder.AssetPairListViewHolder
import com.kohlwage.boschkraken.util.diffCallback
import javax.inject.Inject

class TradingAssetListAdapter @Inject constructor() :
    ListAdapter<TradingOverviewItem, AssetPairListViewHolder>(diffCallback({ this.altname }, { this })) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetPairListViewHolder {
        val binding = AssetListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssetPairListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssetPairListViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

}
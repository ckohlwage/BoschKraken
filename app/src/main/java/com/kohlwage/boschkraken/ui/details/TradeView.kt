package com.kohlwage.boschkraken.ui.details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.kohlwage.boschkraken.databinding.ViewTradeBinding
import com.kohlwage.boschkraken.models.Trade

/**
 * Displays a trade
 */
class TradeView(context: Context, parent: ViewGroup, trade: Trade) : ConstraintLayout(context) {

    init {
        ViewTradeBinding.inflate(LayoutInflater.from(context), parent, true)
            .apply {
                this.trade = trade
            }
    }

}
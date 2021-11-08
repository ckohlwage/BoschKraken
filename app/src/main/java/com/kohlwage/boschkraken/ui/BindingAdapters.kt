package com.kohlwage.boschkraken.ui

import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.kohlwage.boschkraken.models.Trade
import com.kohlwage.boschkraken.ui.details.TradeView
import com.kohlwage.boschkraken.util.DateUtil


@BindingAdapter("date_time")
fun setDateTime(textView: TextView, timeInMillies: Long) {
    textView.text = DateUtil.getDateTimeFromMillies(timeInMillies)
}

@BindingAdapter("add_trades")
fun addTrades(layout: LinearLayout, list: List<Trade>?) {
    layout.removeAllViews()
    list?.forEach {
        layout.addView(TradeView(layout.context, layout, it))
    }

}





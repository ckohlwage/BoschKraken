package com.kohlwage.boschkraken.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/**
 * Helper method to generify the creation of [DiffUtil.ItemCallback] in PagingAdapter implementations.
 */
fun <T, R> diffCallback(identifier: T.() -> R, self: T.() -> T): DiffUtil.ItemCallback<T> =
    object : DiffUtil.ItemCallback<T>() {
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem.self() == newItem.self()
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem.identifier() == newItem.identifier()
    }
package com.example.githubsearch.utils

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow

inline fun <T> LazyListScope.paging(
    items: List<T>,
    currentIndexFlow: StateFlow<Int>,
    threshold: Int = 4,
    pageSize: Int = 20,
    crossinline fetch: () -> Unit,
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) {
    itemsIndexed(items) { index, item ->

        itemContent(item)

        if ((index + threshold + 1) >= pageSize * (currentIndexFlow.value - 1)) {
            fetch()
        }
    }
}
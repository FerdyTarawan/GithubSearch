package com.example.githubsearch.utils

fun formatNumber(number: Long): String {
    if (number < 1000) {
        return number.toString()
    }

    return "${number.div(1000).toString()}K"
}
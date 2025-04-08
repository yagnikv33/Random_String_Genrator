package com.example.tatacodechallenge.data

data class RandomTextWrapper(
    val randomText: RandomText
)

data class RandomText(
    val value: String,
    val length: Int,
    val created: String
)

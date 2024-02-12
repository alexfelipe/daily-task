package br.com.alexf.dailytask.mocks

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlin.random.Random

fun generateLoremAt(
    words: Int = 2
) = LoremIpsum(
    Random.nextInt(1, if (words > 1) words else 2)
).values.first()
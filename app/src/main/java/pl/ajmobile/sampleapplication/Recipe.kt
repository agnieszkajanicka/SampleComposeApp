package pl.ajmobile.sampleapplication

import androidx.annotation.DrawableRes
import androidx.ui.graphics.Image

data class Recipe(
    val title: String,
    val ingredients: List<String>,
    val instructions: String,
    @DrawableRes val imageId: Int,
    val image: Image? = null
)

package pl.ajmobile.sampleapplication

import androidx.compose.Model

sealed class View {
    object Main : View()
    data class Details(val recipe: Recipe) : View()
}

@Model
object NavigationStatus {
    var currentScreen: View = View.Main
}
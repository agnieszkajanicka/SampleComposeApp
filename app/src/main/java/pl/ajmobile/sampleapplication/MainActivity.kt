package pl.ajmobile.sampleapplication

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.ui.animation.Crossfade
import androidx.ui.core.*
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.material.surface.Surface
import androidx.ui.material.themeTextStyle
import androidx.ui.tooling.preview.Preview
import pl.ajmobile.sampleapplication.NavigationStatus.currentScreen

class MainActivity : AppCompatActivity() {

    private lateinit var recipes: List<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipes = getRecipesWithImagesLoaded(testRecipes, resources)

        setContent { //here Compose starts
            MaterialTheme {
                //LiveCodingContentPreview()
                RecipesExample(recipes)
            }
        }
    }
}

@Preview
@Composable
fun LiveCodingContentPreview() {
    val counter = +state { 0 }

    Center {
        Column {
            Text(text = "Clicks: ${counter.value}")
            Button(text = "Button", onClick = {
                counter.value++
            })
            //TODO comment Button to see Preview
        }
    }
}

@Composable
fun RecipesExample(recipes: List<Recipe>) {
    Crossfade(currentScreen) { screen -> //navigation in activity
        when (screen) {
            is View.Main -> AppRecipesContent(recipes, recipeSelected = {
                currentScreen = View.Details(recipe = it)
            })
            is View.Details -> RecipeDetails(screen.recipe, closePressed = {
                currentScreen = View.Main
            })
        }
    }
}

@Composable
fun RecipeDetails(recipe: Recipe, closePressed: () -> Unit) {
    Center {
        Column(crossAxisAlignment = CrossAxisAlignment.Center) {
            RecipePreviewCard {
                RecipeDetailsContent(recipe)
            }
            Button(onClick = closePressed) {
                Text("Close")
            }
        }
    }
}

@Composable
fun RecipeDetailsContent(recipe: Recipe) {
    Column {
        Text(text = recipe.title, style = (+themeTextStyle { h4 }))
        HeightSpacer(height = 16.dp)
        Ingredients(recipe.ingredients)
        HeightSpacer(height = 16.dp)
        Text(text = "Instructions", style = (+themeTextStyle { h5 }))
        Text(text = recipe.instructions)
    }
}

@Composable
fun AppRecipesContent(recipes: List<Recipe>, recipeSelected: (recipe: Recipe) -> Unit = {}) {
    VerticalScroller {
        Column {
            recipes.forEach {
                RecipePreview(it, recipeSelected)
            }
        }
    }
}

@Composable
fun RecipePreview(recipe: Recipe, recipeSelected: (recipe: Recipe) -> Unit) {
    RecipePreviewCard {
        RecipePreviewContent(recipeSelected, recipe)
    }
}

@Composable
fun RecipePreviewContent(
    recipeSelected: (recipe: Recipe) -> Unit,
    recipe: Recipe
) {
    Clickable(onClick = { recipeSelected(recipe) }) {
        Column(crossAxisAlignment = CrossAxisAlignment.Center) {
            recipe.image?.let {
                Container(expanded = true, height = 180.dp) {
                    Clip(shape = RoundedCornerShape(16.dp)) {
                        DrawImage(image = it)
                    }
                }
            }
            Text(
                text = recipe.title,
                style = (+themeTextStyle { h6 })
            )
        }
    }
}

@Composable
fun RecipePreviewCard(children: @Composable() () -> Unit) {
    Ripple(bounded = true) {
        Padding(padding = 16.dp) {
            Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp) {
                Padding(padding = 16.dp) {
                    children()
                }
            }
        }
    }
}

@Composable
fun Ingredients(ingredients: List<String>) {
    Text(text = "You will need:", style = (+themeTextStyle { h5 }))
    ingredients.forEach {
        Text(text = it)
    }
}

fun getRecipesWithImagesLoaded(recipes: List<Recipe>, resources: Resources): List<Recipe> {
    return recipes.map {
        it.copy(
            image = imageFromResource(resources, it.imageId) //loading mocked images from resources
        )
    }
}


package pl.ajmobile.sampleapplication

val applePie = Recipe(
    title = "Apple Pie",
    ingredients = listOf(
        "flour", "sugar", "apple", "milk", "butter", "eggs"
    ),
    instructions = "This is an instruction of making Apple Pie, very long... This is an instruction of making Apple Pie, very long... This is an instruction of making Apple Pie, very long... This is an instruction of making Apple Pie, very long... This is an instruction of making Apple Pie, very long... ",
    imageId = R.drawable.apple_pie
)

val cheeseCake = Recipe(
    title = "Cheese Cake",
    ingredients = listOf(
        "flour", "sugar", "cheese", "milk", "butter", "eggs"
    ),
    instructions = "This is an instruction of making Cheese Cake, very long... This is an instruction of making Cheese Cake, very long... This is an instruction of making Cheese Cake, very long... This is an instruction of making Cheese Cake, very long... This is an instruction of making Cheese Cake, very long...",
    imageId = R.drawable.cheesecake
)

val muffins = Recipe(
    title = "Chocolate Muffins",
    ingredients = listOf(
        "flour", "sugar", "cacao", "milk", "butter", "eggs"
    ),
    instructions = "This is an instruction of making Chocolate Muffins, very long... This is an instruction of making Chocolate Muffins, very long... This is an instruction of making Chocolate Muffins, very long... This is an instruction of making Chocolate Muffins, very long... ",
    imageId = R.drawable.muffins
)

val testRecipes = listOf(applePie, cheeseCake, muffins, applePie, cheeseCake, muffins)
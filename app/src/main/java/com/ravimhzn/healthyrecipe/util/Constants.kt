package com.ravimhzn.healthyrecipe.util

class Constants {
    companion object {
        const val BASE_URL = "https://recipesapi.herokuapp.com"
        const val NETWORK_TIMEOUT = 3000

        val DEFAULT_SEARCH_CATEGORIES = arrayOf(
            "Barbeque",
            "Breakfast",
            "Chicken",
            "Beef",
            "Brunch",
            "Dinner",
            "Wine",
            "Italian"
        )

        val DEFAULT_SEARCH_CATEGORY_IMAGES = arrayOf(
            "barbeque",
            "breakfast",
            "chicken",
            "beef",
            "brunch",
            "dinner",
            "wine",
            "italian"
        )
    }
}
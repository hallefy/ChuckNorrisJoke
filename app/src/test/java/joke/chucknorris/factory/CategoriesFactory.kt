package joke.chucknorris.factory

import joke.chucknorris.features.joke.repository.model.JokeResponse

fun getCategoriesFake() = mutableListOf("music", "dance", "humor")

fun getJokeFake() = JokeResponse(
    mutableListOf("music"), "imageUrl", "1", "url", "is a joke"
)
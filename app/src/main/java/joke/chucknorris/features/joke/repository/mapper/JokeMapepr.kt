package joke.chucknorris.features.joke.repository.mapper

import joke.chucknorris.features.joke.repository.model.JokeResponse
import joke.chucknorris.features.joke.viewmodel.model.JokeData

fun JokeResponse.toJokeData(): JokeData {
    return JokeData(
        icon = icon_url,
        joke = value,
        category = categories.firstOrNull() ?: "Empty"
    )
}
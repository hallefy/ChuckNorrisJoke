package joke.chucknorris.features.joke.repository.model

data class JokeResponse(
    val categories: List<String>,
    val icon_url: String,
    val id: String,
    val url: String,
    val value: String
)
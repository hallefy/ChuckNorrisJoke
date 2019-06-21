package joke.chucknorris.features.joke.repository

import joke.chucknorris.commons.utils.ResourceResponse
import joke.chucknorris.commons.utils.toResourceResponse
import joke.chucknorris.commons.worker.NetworkRequest
import joke.chucknorris.features.joke.repository.mapper.toJokeData
import joke.chucknorris.features.joke.repository.service.JokeService
import joke.chucknorris.features.joke.viewmodel.model.JokeData
import javax.inject.Inject

interface JokeRepository {
    fun getJoke(category: String): ResourceResponse<JokeData>
}

class JokeRepositoryImpl @Inject constructor(
    private val worker: NetworkRequest,
    private val service: JokeService
) : JokeRepository {

    override fun getJoke(category: String): ResourceResponse<JokeData> {
        return worker.request(service.getJoke(category))
            .map { it.toJokeData() }
            .toResourceResponse()
    }
}
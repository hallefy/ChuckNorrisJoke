package joke.chucknorris.features.joke.repository.service

import io.reactivex.Observable
import joke.chucknorris.commons.utils.URLs
import joke.chucknorris.features.joke.repository.model.JokeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeService {

    @GET(URLs.JOKE)
    fun getJoke(@Query("category") category: String) : Observable<JokeResponse>
}
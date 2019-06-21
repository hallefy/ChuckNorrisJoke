package joke.chucknorris.features.joke.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import joke.chucknorris.commons.utils.ResourceResponse
import joke.chucknorris.commons.utils.ViewState
import joke.chucknorris.features.joke.repository.JokeRepository
import joke.chucknorris.features.joke.viewmodel.model.JokeData
import javax.inject.Inject

class JokeViewModel @Inject constructor(
    val repository: JokeRepository
) : ViewModel() {

    private val jokeRequest = MutableLiveData<ResourceResponse<JokeData>>()

    val jokeData: LiveData<JokeData> = switchMap(jokeRequest) { it.data }
    val jokeViewState: LiveData<ViewState> = switchMap(jokeRequest) { it.state }

    fun getJoke(category: String) {
        jokeRequest.value = repository.getJoke(category)
    }
}
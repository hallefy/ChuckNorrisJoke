package joke.chucknorris.utils

import androidx.lifecycle.MutableLiveData
import joke.chucknorris.commons.utils.ResourceResponse
import joke.chucknorris.commons.utils.ViewState

fun <T> mockResourceResponse(retry: (() -> ResourceResponse<T>)? = null): ResourceResponse<T> {
    return ResourceResponse(
        data = MutableLiveData<T>(),
        state = MutableLiveData<ViewState>(),
        message = MutableLiveData<String>()
    )
}
package joke.chucknorris.commons.utils

import androidx.lifecycle.LiveData

class ResourceResponse<T> (
    val state: LiveData<ViewState>,
    val data: LiveData<T>,
    val message: LiveData<String>
)

enum class ViewState {
    LOADING, SUCCESS, ERROR
}
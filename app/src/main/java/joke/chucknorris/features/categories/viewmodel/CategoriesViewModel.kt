package joke.chucknorris.features.categories.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import joke.chucknorris.commons.utils.ResourceResponse
import joke.chucknorris.commons.utils.ViewState
import joke.chucknorris.features.categories.repository.CategoriesRepository
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    val repository: CategoriesRepository
) : ViewModel() {

    private val categoriesRequest = MutableLiveData<ResourceResponse<MutableList<String>>>()

    val categoriesData: LiveData<MutableList<String>> = switchMap(categoriesRequest) { it.data }
    val categoriesViewState: LiveData<ViewState> = switchMap(categoriesRequest) { it.state }

    fun getCategories() {
        categoriesRequest.value =  repository.getCategories()
    }
}
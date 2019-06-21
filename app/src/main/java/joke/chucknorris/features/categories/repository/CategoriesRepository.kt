package joke.chucknorris.features.categories.repository

import android.annotation.SuppressLint
import dagger.Component
import joke.chucknorris.commons.worker.NetworkRequest
import joke.chucknorris.commons.utils.ResourceResponse
import joke.chucknorris.commons.utils.toResourceResponse
import joke.chucknorris.features.categories.repository.service.CategoriesService
import javax.inject.Inject

interface CategoriesRepository {
    fun getCategories(): ResourceResponse<MutableList<String>>
}

class CategoriesRepositoryImpl @Inject constructor(
    private val worker: NetworkRequest,
    private val service: CategoriesService
) : CategoriesRepository {

    @SuppressLint("CheckResult")
    override fun getCategories(): ResourceResponse<MutableList<String>> {
        return worker.request(service.getCategories()).toResourceResponse()
    }
}
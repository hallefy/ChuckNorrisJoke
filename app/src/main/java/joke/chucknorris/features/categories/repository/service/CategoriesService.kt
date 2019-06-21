package joke.chucknorris.features.categories.repository.service

import io.reactivex.Observable
import joke.chucknorris.commons.utils.URLs
import retrofit2.http.GET

interface CategoriesService {

    @GET(URLs.CATEGORIES)
    fun getCategories() : Observable<MutableList<String>>
}
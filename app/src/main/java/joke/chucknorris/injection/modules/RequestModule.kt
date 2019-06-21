package joke.chucknorris.injection.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import joke.chucknorris.features.categories.repository.service.CategoriesService
import joke.chucknorris.features.joke.repository.service.JokeService
import retrofit2.Retrofit
import javax.inject.Named

@Module
class RequestModule {

    @Provides
    @Reusable
    fun provideCategoriesService(
        @Named("RetrofitApi") retrofit: Retrofit
    ): CategoriesService {
        return retrofit.create(CategoriesService::class.java)
    }

    @Provides
    @Reusable
    fun provideJokeService(
        @Named("RetrofitApi") retrofit: Retrofit
    ): JokeService {
        return retrofit.create(JokeService::class.java)
    }
}
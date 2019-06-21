package joke.chucknorris.injection.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import joke.chucknorris.commons.worker.NetworkRequest
import joke.chucknorris.commons.worker.SchedulersFacade
import joke.chucknorris.commons.worker.SchedulersFacadeImpl
import joke.chucknorris.features.categories.repository.CategoriesRepository
import joke.chucknorris.features.categories.repository.CategoriesRepositoryImpl
import joke.chucknorris.features.categories.repository.service.CategoriesService
import joke.chucknorris.features.joke.repository.JokeRepository
import joke.chucknorris.features.joke.repository.JokeRepositoryImpl
import joke.chucknorris.features.joke.repository.service.JokeService
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    internal fun providerApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Reusable
    internal fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    fun provideSchedulers(schedulers: SchedulersFacadeImpl): SchedulersFacade = schedulers

    @Provides
    @Singleton
    fun provideCategoriesRepository(
        worker: NetworkRequest,
        service: CategoriesService
    ): CategoriesRepository = CategoriesRepositoryImpl(worker, service)

    @Provides
    @Singleton
    fun provideJokeRepository(
        worker: NetworkRequest,
        service: JokeService
    ): JokeRepository = JokeRepositoryImpl(worker, service)
}
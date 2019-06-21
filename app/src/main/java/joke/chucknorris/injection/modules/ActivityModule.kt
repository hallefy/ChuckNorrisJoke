package joke.chucknorris.injection.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import joke.chucknorris.features.categories.view.ActivityCategories
import joke.chucknorris.features.joke.view.ActivityJoke

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun bindCategories(): ActivityCategories

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun bindJoke(): ActivityJoke
}
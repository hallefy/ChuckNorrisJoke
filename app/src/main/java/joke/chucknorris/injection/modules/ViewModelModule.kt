package joke.chucknorris.injection.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import joke.chucknorris.commons.utils.ViewModelKey
import joke.chucknorris.features.categories.viewmodel.CategoriesViewModel
import joke.chucknorris.features.joke.viewmodel.JokeViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    internal abstract fun provideCategoriesViewModel(viewModel: CategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(JokeViewModel::class)
    internal abstract fun provideJokeViewModel(viewModel: JokeViewModel): ViewModel
}
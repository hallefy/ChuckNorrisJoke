package joke.chucknorris.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import joke.chucknorris.App
import joke.chucknorris.injection.modules.ActivityModule
import joke.chucknorris.injection.modules.ApplicationModule
import joke.chucknorris.injection.modules.NetworkModule
import joke.chucknorris.injection.modules.RequestModule
import joke.chucknorris.injection.modules.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = ([
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        RequestModule::class,
        ViewModelModule::class
    ])
)

interface ApplicationComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
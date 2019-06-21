package joke.chucknorris

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import joke.chucknorris.injection.DaggerApplicationComponent

class App : DaggerApplication() {

    private lateinit var applicaton: Application

    override fun onCreate() {
        super.onCreate()
        applicaton = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerApplicationComponent.builder().application(this).build()
        appComponent.inject(this)

        return appComponent
    }
}

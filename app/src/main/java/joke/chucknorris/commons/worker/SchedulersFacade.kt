package joke.chucknorris.commons.worker

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface SchedulersFacade {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}

class SchedulersFacadeImpl @Inject constructor() : SchedulersFacade {
    override fun io() = Schedulers.io()
    override fun ui() = AndroidSchedulers.mainThread()
    override fun computation() = Schedulers.computation()
}
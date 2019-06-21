package joke.chucknorris.commons.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.internal.operators.observable.ObservableFromCallable

fun <T> Observable<T>.baseResult(): Observable<T> {
    return this.compose(handle())
}

private fun <T> handle(): ObservableTransformer<T, T> {
    return ObservableTransformer { observable ->
        observable.flatMap { t ->
            Observable.defer {
                ObservableFromCallable.fromCallable { t }
            }
        }
    }
}

fun <T> buildResourceResponse(): Triple<MutableLiveData<T>, MutableLiveData<ViewState>, MutableLiveData<String>> {
    return Triple(MutableLiveData(), MutableLiveData(), MutableLiveData())
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { action(it) } })
}

val <T> LiveData<T>.asMutable: MutableLiveData<T>
    get() = this as? MutableLiveData<T> ?: throw IllegalStateException("$this isn't a valid MutableLiveData")

@SuppressLint("CheckResult")
fun <T> Observable<T>.toResourceResponse(): ResourceResponse<T> {
    val (result, state, errorMessage) = buildResourceResponse<T>()

    this.doOnSubscribe {
        state.postValue(ViewState.LOADING)
    }.subscribe({ data ->
        result.postValue(data)
        state.postValue(ViewState.SUCCESS)
    }, {
        state.postValue(ViewState.ERROR)
        errorMessage.postValue(it.message)
    })

    return ResourceResponse(
        data = result,
        state = state,
        message = errorMessage
    )
}

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun ImageView.loadImg(url: String) {
    Picasso.get().load(url).into(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.groupVisible() {
    this.visibility = ViewGroup.VISIBLE
}

fun View.groupGone() {
    this.visibility = ViewGroup.GONE
}
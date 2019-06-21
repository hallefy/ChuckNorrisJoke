package joke.chucknorris.features.joke.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import joke.chucknorris.R
import joke.chucknorris.commons.utils.ViewModelFactory
import joke.chucknorris.commons.utils.ViewState
import joke.chucknorris.commons.utils.gone
import joke.chucknorris.commons.utils.groupGone
import joke.chucknorris.commons.utils.groupVisible
import joke.chucknorris.commons.utils.loadImg
import joke.chucknorris.commons.utils.observe
import joke.chucknorris.commons.utils.visible
import joke.chucknorris.features.joke.viewmodel.JokeViewModel
import joke.chucknorris.features.joke.viewmodel.model.JokeData
import kotlinx.android.synthetic.main.activity_joke.*
import javax.inject.Inject

class ActivityJoke : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: JokeViewModel

    private var categoryType = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[JokeViewModel::class.java]
        getIntentCategory()
        addObservers()
        setupView()

        viewModel.getJoke(categoryType)
    }

    private fun setupView() {

        btnJoke.setOnClickListener {
            viewModel.getJoke(categoryType)
        }
    }

    private fun getIntentCategory() {
        categoryType = intent.getStringExtra(CATEGORY_NAME)
    }

    private fun addObservers() {
        observe(viewModel.jokeViewState) {
            when(it) {
                ViewState.LOADING -> {
                    setLoading()
                }
                ViewState.SUCCESS -> {
                    setDetailVisible()
                }
                ViewState.ERROR -> {
                    setError()
                }
            }
        }

        observe(viewModel.jokeData) {
            setJoke(it)
        }
    }

    private fun setError() {
        progressBar.gone()
        groupDetail.groupGone()
        groupError.groupVisible()
    }

    private fun setDetailVisible() {
        progressBar.gone()
        groupError.groupGone()
        groupDetail.groupVisible()
    }

    private fun setLoading() {
        progressBar.visible()
        groupDetail.groupGone()
        groupError.groupGone()
    }

    private fun setJoke(jokeData: JokeData) {
        jokeData.let {
            tvJoke.text = it.joke
            ivJoke.loadImg(it.icon)
            tvCategoryType.text = it.category
        }
    }

    companion object {
        private const val CATEGORY_NAME = "category"

        fun getIntent(categoryName: String, context: Context) = Intent(context, ActivityJoke::class.java).apply {
            putExtra(CATEGORY_NAME, categoryName)
        }
    }
}

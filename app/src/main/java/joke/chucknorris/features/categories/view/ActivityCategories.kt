package joke.chucknorris.features.categories.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import joke.chucknorris.R
import joke.chucknorris.commons.utils.ViewModelFactory
import joke.chucknorris.commons.utils.ViewState
import joke.chucknorris.commons.utils.gone
import joke.chucknorris.commons.utils.groupGone
import joke.chucknorris.commons.utils.groupVisible
import joke.chucknorris.commons.utils.observe
import joke.chucknorris.commons.utils.visible
import joke.chucknorris.features.categories.view.adapter.CategoriesAdapter
import joke.chucknorris.features.categories.viewmodel.CategoriesViewModel
import joke.chucknorris.features.joke.view.ActivityJoke
import kotlinx.android.synthetic.main.activity_main.*

import javax.inject.Inject

class ActivityCategories : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: CategoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[CategoriesViewModel::class.java]

        getCategories()
        addObservers()
        setupView()
    }

    private fun setupView() {
        btnJoke.setOnClickListener {
            viewModel.getCategories()
        }
    }

    private fun addObservers() {

        observe(viewModel.categoriesData) {
            addCategories(it)
        }

        observe(viewModel.categoriesViewState) {
            when (it) {
                ViewState.SUCCESS -> setSuccess()
                ViewState.LOADING -> setLoading()
                ViewState.ERROR -> setError()
            }
        }
    }

    private fun setSuccess() {
        groupError.groupGone()
        recycler.visible()
        progressBar.gone()
    }

    private fun setLoading() {
        groupError.groupGone()
        recycler.gone()
        progressBar.visible()
    }

    private fun setError() {
        groupError.groupVisible()
        recycler.gone()
        progressBar.gone()
    }

    private fun addCategories(categories: MutableList<String>) {
        with(recycler) {
            layoutManager = LinearLayoutManager(this@ActivityCategories)
            adapter = CategoriesAdapter(categories) {
                startActivity(ActivityJoke.getIntent(it, this@ActivityCategories))
            }
        }
    }

    private fun getCategories() {
        viewModel.getCategories()
    }
}
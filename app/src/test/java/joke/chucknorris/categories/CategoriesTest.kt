package joke.chucknorris.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import joke.chucknorris.commons.utils.ViewState
import joke.chucknorris.commons.utils.asMutable
import joke.chucknorris.factory.getCategoriesFake
import joke.chucknorris.features.categories.repository.CategoriesRepositoryImpl
import joke.chucknorris.features.categories.viewmodel.CategoriesViewModel
import joke.chucknorris.utils.mockResourceResponse
import joke.chucknorris.utils.test
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoriesTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val repository = mockk<CategoriesRepositoryImpl>()
    private lateinit var viewModel: CategoriesViewModel

    @Before
    fun setUp() {
        viewModel = CategoriesViewModel(
            repository
        )
    }

    @Test
    fun `when request categories with success should call ViewState SUCCESS`() {
        shouldReturnSuccess()

        viewModel.getCategories()

        verify(exactly = 1) {
            repository.getCategories()
        }

        viewModel.categoriesData.test()
            .assertValue(getCategoriesFake(), 1)

        viewModel.categoriesViewState.test()
            .assertValue(ViewState.SUCCESS, 1)
    }

    @Test
    fun `when request categories with success should call ViewState ERROR`() {
        shouldReturnError()

        viewModel.getCategories()

        verify(exactly = 1) {
            repository.getCategories()
        }

        viewModel.categoriesData.test()
            .assertNotInvoked()

        viewModel.categoriesViewState.test()
            .assertValue(ViewState.ERROR, 1)
    }

    @Test
    fun `when request categories with success should call ViewState LOADING`() {
        shouldReturnLading()

        viewModel.getCategories()

        verify(exactly = 1) {
            repository.getCategories()
        }

        viewModel.categoriesData.test()
            .assertNotInvoked()

        viewModel.categoriesViewState.test()
            .assertValue(ViewState.LOADING, 1)
    }

    private fun shouldReturnError() {
        val resourceResponse = mockResourceResponse<MutableList<String>>().apply {
            state.asMutable.postValue(ViewState.ERROR)
        }

        every {
            repository.getCategories()
        }.returns(resourceResponse)
    }

    private fun shouldReturnLading() {
        val resourceResponse = mockResourceResponse<MutableList<String>>().apply {
            state.asMutable.postValue(ViewState.LOADING)
        }

        every {
            repository.getCategories()
        }.returns(resourceResponse)
    }

    private fun shouldReturnSuccess() {
        val resourceResponse = mockResourceResponse<MutableList<String>>().apply {
            state.asMutable.postValue(ViewState.SUCCESS)
            data.asMutable.postValue(getCategoriesFake())
        }

        every {
            repository.getCategories()
        }.returns(resourceResponse)
    }
}

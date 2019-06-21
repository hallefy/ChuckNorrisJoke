package joke.chucknorris.joke

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import joke.chucknorris.commons.utils.ViewState
import joke.chucknorris.commons.utils.asMutable
import joke.chucknorris.factory.getJokeFake
import joke.chucknorris.features.joke.repository.JokeRepositoryImpl
import joke.chucknorris.features.joke.repository.mapper.toJokeData
import joke.chucknorris.features.joke.viewmodel.JokeViewModel
import joke.chucknorris.features.joke.viewmodel.model.JokeData
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

    private val repository = mockk<JokeRepositoryImpl>()
    private lateinit var viewModel: JokeViewModel

    @Before
    fun setUp() {
        viewModel = JokeViewModel(
            repository
        )
    }

    @Test
    fun `when request categories with success should call ViewState SUCCESS`() {
        shouldReturnSuccess()

        viewModel.getJoke(JOKE_CATEGORY)

        verify(exactly = 1) {
            repository.getJoke(JOKE_CATEGORY)
        }

        viewModel.jokeData.test()
            .assertValue(getJokeFake().toJokeData(), 1)

        viewModel.jokeViewState.test()
            .assertValue(ViewState.SUCCESS, 1)
    }

    @Test
    fun `when request categories with success should call ViewState ERROR`() {
        shouldReturnError()

        viewModel.getJoke(JOKE_CATEGORY)

        verify(exactly = 1) {
            repository.getJoke(JOKE_CATEGORY)
        }

        viewModel.jokeData.test()
            .assertNotInvoked()

        viewModel.jokeViewState.test()
            .assertValue(ViewState.ERROR, 1)
    }

    @Test
    fun `when request categories with success should call ViewState LOADING`() {
        shouldReturnLading()

        viewModel.getJoke(JOKE_CATEGORY)

        verify(exactly = 1) {
            repository.getJoke(JOKE_CATEGORY)
        }

        viewModel.jokeData.test()
            .assertNotInvoked()

        viewModel.jokeViewState.test()
            .assertValue(ViewState.LOADING, 1)
    }

    private fun shouldReturnError() {
        val resourceResponse = mockResourceResponse<JokeData>().apply {
            state.asMutable.postValue(ViewState.ERROR)
        }

        every {
            repository.getJoke(JOKE_CATEGORY)
        }.returns(resourceResponse)
    }

    private fun shouldReturnLading() {
        val resourceResponse = mockResourceResponse<JokeData>().apply {
            state.asMutable.postValue(ViewState.LOADING)
        }

        every {
            repository.getJoke(JOKE_CATEGORY)
        }.returns(resourceResponse)
    }

    private fun shouldReturnSuccess() {
        val resourceResponse = mockResourceResponse<JokeData>().apply {
            state.asMutable.postValue(ViewState.SUCCESS)
            data.asMutable.postValue(getJokeFake().toJokeData())
        }

        every {
            repository.getJoke(JOKE_CATEGORY)
        }.returns(resourceResponse)
    }

    companion object {
        const val JOKE_CATEGORY = "music"
    }
}

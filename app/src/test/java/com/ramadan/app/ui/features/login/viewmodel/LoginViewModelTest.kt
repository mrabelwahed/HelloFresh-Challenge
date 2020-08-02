package com.ramadan.app.ui.features.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ramadan.app.error.Failure
import com.ramadan.app.state.ViewState
import com.ramadan.domain.interactor.LoginUser
import com.ramadan.domain.repository.UserRepository
import com.ramadan.test_utils.RxSchedulerRule
import io.reactivex.Single
import mock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import java.net.UnknownHostException

class LoginViewModelTest {
    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()!!

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var getNewsList: LoginUser

    @Mock
    var observer: Observer<ViewState> = mock()

    @Rule
    @JvmField
    var testSchedulerRule: RxSchedulerRule =
        RxSchedulerRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        getNewsList = LoginUser(userRepository)
        loginViewModel = LoginViewModel(getNewsList)
        loginViewModel.liveUIState.observeForever(observer)
    }

    @Test
    fun `view model is ready for test`() {
        assertNotNull(loginViewModel)
    }

    @Test
    fun `should be able to login successfully`() {
        // given
        given(
            userRepository.login(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
            )
        ).willReturn(
            Single.just(true)
        )
        // when
        userRepository.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        loginViewModel.uiState.value = ViewState.Success(Single.just(true))
        // then
        val captor = ArgumentCaptor.forClass(ViewState::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(loginViewModel.uiState.value, value)
        }
    }


    @Test
    fun `no internet error case`() {
        // given
        given(userRepository.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .willReturn(Single.error(UnknownHostException()))
        // when
        userRepository.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        loginViewModel.uiState.value = ViewState.Error(Failure.NetworkConnection)
        // then
        val captor = ArgumentCaptor.forClass(ViewState::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(value, ViewState.Error(Failure.NetworkConnection))
        }
    }

}
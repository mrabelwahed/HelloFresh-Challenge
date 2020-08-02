package com.ramadan.domain.interactor

import com.ramadan.domain.repository.UserRepository
import com.ramadan.test_utils.RxSchedulerRule
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

const val EMAIL = "admin@hellofresh.com"
const val PASSWORD = "123456"

class LoginUserTest {
    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()!!
    private lateinit var loginUser: LoginUser

    @Mock
    lateinit var userRepository: UserRepository

    @Rule
    @JvmField
    var testSchedulerRule: RxSchedulerRule =
        RxSchedulerRule()

    private val testSubscriber = TestObserver<Boolean>()

    @Before
    fun setup() {
        loginUser = LoginUser(userRepository)
    }

    @Test
    fun `LoginUser usecase is ready for test`() {
        assertNotNull(loginUser)
    }

    @Test
    fun `should login successfully for valid email and password`() {
        given(userRepository.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .willReturn(Single.just(true))
        loginUser.setLoginData(EMAIL, PASSWORD)
        loginUser.execute(Unit).subscribe(testSubscriber)
        testSubscriber.assertNoErrors().assertComplete()
        assertEquals("success login", testSubscriber.values()[0], true)
    }

    @Test
    fun `should not login successfully for not valid email and password`() {
        given(userRepository.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .willReturn(Single.just(false))
        loginUser.setLoginData(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        loginUser.execute(Unit).subscribe(testSubscriber)
        testSubscriber.assertNoErrors().assertComplete()
        assertEquals("login failed", testSubscriber.values()[0], false)
    }

}



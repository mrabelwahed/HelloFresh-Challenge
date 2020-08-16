package com.ramadan.app.ui.features.login.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.ramadan.app.R
import com.ramadan.app.RecipeApp
import com.ramadan.app.di.component.DaggerAppComponent
import com.ramadan.app.di.component.DaggerLoginActivityComponent
import com.ramadan.app.di.module.UserRepositoryModule
import com.ramadan.app.di.vmfactory.ViewModelFactory
import com.ramadan.app.state.ViewState
import com.ramadan.app.ui.features.login.viewmodel.LoginViewModel
import com.ramadan.app.ui.features.recipes.model.Recipe
import com.ramadan.app.ui.features.recipes.view.RecipesActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initDI()
        loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        loginBtn.setOnClickListener {
            this.hideKeyboard()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty())
                loginViewModel.login(email,password)
            else
                Snackbar.make(loginView,"enter valid email and password",Snackbar.LENGTH_SHORT).show()
        }

        observeLoginState()
    }

    private fun observeLoginState() {
        loginViewModel.liveUIState.observeForever {
            when (it) {
                is ViewState.Success<*> -> {
                    val loginStatus = it.item as Boolean
                    handleUISuccess(loginStatus)
                }
                is ViewState.Error -> {
                    handleUIError()
                }
            }
        }
    }

    private fun handleUISuccess(loginStatus : Boolean) {
        if (loginStatus){
            Intent(application,RecipesActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }else{
            Snackbar.make(loginView,"enter valid email and password",Snackbar.LENGTH_SHORT).show()
        }

    }

    private fun handleUIError() {
        Toast.makeText(applicationContext,"something wen wrong",Toast.LENGTH_SHORT).show()
    }

    private fun initDI() {
        val appComponent = (application as RecipeApp).getApplicationComponent()
        val userActivityComponent = DaggerLoginActivityComponent.builder()
            .appComponent(appComponent)
            .userRepositoryModule(UserRepositoryModule())
            .build()
        userActivityComponent.inject(this)
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(applicationContext))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
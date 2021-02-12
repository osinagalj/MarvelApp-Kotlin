package com.intermedia.challenge.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.intermedia.challenge.ui.main.MainScreenActivity
import com.intermedia.challenge.R
import kotlinx.android.synthetic.main.activity_login.*
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private val GOOGLE_SIGN_IN = 100
    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setup()

    }

    override fun onResume() {
        super.onResume()
        startFirebaseAuth()
    }

    private fun startFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        if(user != null){
           // startActivity(Intent(this, MainScreenActivity::class.java).apply {
            //    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
           //     addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
           // })
        }

    }

    private fun showMainScreen() {
        val homeIntent = Intent(this, MainScreenActivity::class.java)
        //It is possible to pass data if it is requiere
        startActivity(homeIntent)
    }

    private fun setup() {

        //auth google button
        btn_google.setOnClickListener {

            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }

        //auth Facebook button
        btn_facebook.setOnClickListener {

            LoginManager.getInstance().logOut()
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

            LoginManager.getInstance().registerCallback( callbackManager,
                object : FacebookCallback<LoginResult> {

                    override fun onSuccess(result: LoginResult?) {

                        result?.let {
                            val token = it.accessToken
                            val credential = FacebookAuthProvider.getCredential(token.token)

                            FirebaseAuth.getInstance().signInWithCredential(credential)
                                .addOnCompleteListener {

                                    if (it.isSuccessful) {
                                        showMainScreen()
                                    } else {
                                        showAlert()
                                    }

                                }
                        }
                    }

                    override fun onCancel() {
                        TODO("Not yet implemented")
                    }

                    override fun onError(error: FacebookException?) {
                        TODO("Not yet implemented")
                        showAlert()
                    }
                })

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {

                            if (it.isSuccessful) {
                                showMainScreen()
                            } else {
                                showAlert()
                            }
                        }
                }
            } catch (e: ApiException) {
                showAlert()
            }

        }
    }


    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error auntenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

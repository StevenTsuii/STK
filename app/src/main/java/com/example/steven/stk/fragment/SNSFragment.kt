package com.example.steven.stk.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.BuildConfig
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseFragment
import com.example.steven.stk.extension.plugFragmentComponent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sns.*
import javax.inject.Inject


class SNSFragment: BaseFragment(){

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    private val RC_GOOGLE_SIGN_IN = 14222

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sns, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plugFragmentComponent().inject(this)

        Log.d(TAG, "Current BuildType: ${BuildConfig.BUILD_TYPE}   isDebug:${BuildConfig.DEBUG}")

        email_password_create_button.setOnClickListener { createUserWithEmailAndPassword() }
        email_password_login_button.setOnClickListener { signInWithEmailAndPassword() }
        anonymously_login_button.setOnClickListener { signInAnonymously() }
        user_profile_button.setOnClickListener { getCurrentUserProfile() }
        link_anonymously_to_email_button.setOnClickListener { linkAnonymousToEmail() }
        google_sign_in_button.setOnClickListener { signInWithGoogle()  }

        val account = GoogleSignIn.getLastSignedInAccount(activity)
        account?.let {
            Log.d(TAG, "Last SignedInAccount success displayName:${it.displayName}")
        }?:kotlin.run {
            Log.d(TAG, "Last SignedInAccount failure")
        }

    }

    fun signInWithGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        activity?.startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode === RC_GOOGLE_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            Log.d(TAG, "signInWithGoogleResult displayName:${account?.displayName} email:${account?.email}")
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d(TAG, "signInWithGoogleResult:failed code=" + e.statusCode +" ${e.message} ")

        }

    }

    fun createUserWithEmailAndPassword(){

        var email = emailEditText.text ?: ""
        var password = passwordEditText.text ?: ""

        firebaseAuth.createUserWithEmailAndPassword(email.toString(), password.toString()).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d(TAG, "Create user success email:$email password:$password")
                getCurrentUserProfile()
            }else{
                Log.d(TAG, "create user failure ${it.exception?.message}")
            }
        }
    }

    fun signInWithEmailAndPassword(){
        var email = emailEditText.text ?: ""
        var password = passwordEditText.text ?: ""

        firebaseAuth.signInWithEmailAndPassword(email.toString(), password.toString()).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d(TAG, "SignInWithEmailAndPassword success    credential:${EmailAuthProvider.getCredential(email.toString(), password.toString())}")
                getCurrentUserProfile()
            }else{
                Log.d(TAG, "SignInWithEmailAndPassword failure ${it.exception?.message}")
            }
        }
    }

    fun signInAnonymously(){
        firebaseAuth.signInAnonymously().addOnCompleteListener {
            if(it.isSuccessful){
                Log.d(TAG, "SsignInAnonymously success")
                getCurrentUserProfile()
            }else{
                Log.d(TAG, "SsignInAnonymously failure ${it.exception?.message}")
            }
        }
    }

    fun getCurrentUserProfile(){
       firebaseAuth.currentUser?.let{user->
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            val uid = user.uid

           Log.d(TAG, "getCurrentUserProfile \nname:$name\n" +
                   "email:$email\n"+
                   "photoUrl:$photoUrl\n"+
                   "emailVerified:$emailVerified\n"+
                   "uid:$uid\n");
        }?:run{
           Log.d(TAG, "getCurrentUserProfile failure")
        }
    }

    fun linkAnonymousToEmail(){
        var credential = EmailAuthProvider.getCredential(emailEditText.text.toString(), passwordEditText.text.toString())
        firebaseAuth.currentUser?.linkWithCredential(credential)?.addOnCompleteListener {task->
            if (task.isSuccessful()) {
                Log.d(TAG, "linkAnonymousToEmail:success")
                val user = task.getResult()?.getUser()
            } else {
                Log.w(TAG, "linkAnonymousToEmail:failure", task.getException())

            }
        }
    }
}
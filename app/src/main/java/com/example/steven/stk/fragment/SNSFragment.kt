package com.example.steven.stk.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseFragment
import com.example.steven.stk.extension.plugFragmentComponent
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sns.*
import javax.inject.Inject





class SNSFragment: BaseFragment(){

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sns, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plugFragmentComponent().inject(this)

        email_password_create_button.setOnClickListener { createUserWithEmailAndPassword() }
        email_password_login_button.setOnClickListener { signInWithEmailAndPassword() }
        anonymously_login_button.setOnClickListener { signInAnonymously() }
        user_profile_button.setOnClickListener { getCurrentUserProfile() }
        link_anonymously_to_email_button.setOnClickListener { linkAnonymousToEmail() }
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
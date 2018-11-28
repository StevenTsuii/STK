package com.example.steven.stk.module

import android.content.Context
import com.example.steven.stk.annotation.ActivityScope
import com.example.steven.stk.data.FakeData2
import com.example.steven.stk.repo.AppRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by steven on 25/4/2018.
 */
@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun providesAppRepository(context: Context) = AppRepository(context)


    @Provides
    @ActivityScope
    fun providesCompositeDisposable() = CompositeDisposable()


    @Provides
    @ActivityScope
    fun providesFakeData2() = FakeData2()

    @Provides
    @ActivityScope
    fun providesFirebaseFirestore(): FirebaseFirestore {
        var firestore = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build()
        FirebaseFirestore.setLoggingEnabled(true)
        firestore.firestoreSettings = settings
        return firestore
    }

    @Provides
    @ActivityScope
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @ActivityScope
    fun providesGoogleSignInClient(context: Context): GoogleSignInClient {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        return GoogleSignIn.getClient(context, gso);
    }
}
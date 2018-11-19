package com.example.steven.stk.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.R
import com.example.steven.stk.activity.SnsActivity
import com.example.steven.stk.base.activity.BaseFragment
import com.example.steven.stk.extension.plugFragmentComponent
import com.example.steven.stk.extension.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_my_cloud.*
import javax.inject.Inject


class MyCloudFragment : BaseFragment() {

    private val TAG = this.javaClass.simpleName
    private val RECORD_A = "recordaa"
    private val RECORD_B = "recordbb"
    @Inject
    lateinit var firestore: FirebaseFirestore
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_cloud, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plugFragmentComponent().inject(this)

        create_account_page.setOnClickListener { activity?.startActivity<SnsActivity>() }

        get_id_token.setOnClickListener { retrieveIdToken() }

        add_record_a_1.setOnClickListener { addRecord1(RECORD_A) }
        add_record_a_2.setOnClickListener { addRecord2(RECORD_A) }

        add_record_b_1.setOnClickListener { addRecord1(RECORD_B) }
        add_record_b_2.setOnClickListener { addRecord2(RECORD_B) }

        read_record_a.setOnClickListener { readRecord(RECORD_A) }
        read_record_b.setOnClickListener { readRecord(RECORD_B) }

//        val providers = arrayListOf(
//                AuthUI.IdpConfig.EmailBuilder().build(),
//                AuthUI.IdpConfig.GoogleBuilder().build())
//
//// Create and launch sign-in intent
//        startActivityForResult(
//                AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .build(),
//                10001)


    }

    fun retrieveIdToken() {
        Log.d(TAG, "retrieveIdToken...")
        val mUser = firebaseAuth.currentUser
        mUser?.let {

            Log.d(TAG, "mUser isAnonymous: ${it.isAnonymous} metadata:${it.metadata}")

            it.getIdToken(true)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val idToken = task.result?.token
                            // Send token to your backend via HTTPS
                            // ...
                            Log.d(TAG, "retrieveIdToken success idToken:$idToken ")
                        } else {
                            // Handle error -> task.getException();
                            Log.d(TAG, "retrieveIdToken failure")
                        }
                    }
        }?:kotlin.run { Log.d(TAG, "User = null") }
    }

    fun getDBGuestPath(): CollectionReference {
        return firestore.collection("membership")
                .document("user_type")
                .collection("guest")
    }

    fun getDBMemberPath(): CollectionReference {
        return firestore.collection("membership")
                .document("user_type")
                .collection("member")
    }

    var x = 0
    var y = 0
    fun addRecord1(collectionName: String) {
        var hashMap = HashMap<String, Any>()
        hashMap.let { hashMap ->
            hashMap["Name"] = "Steven"
            hashMap["Timestamp"] = System.currentTimeMillis()
            hashMap["friendList"] = arrayListOf("AAA", "BBB", "CCC", "DDD")
//            hashMap["Object"] = TestData()

            Log.d(TAG, "add Record1......")
            getDBMemberPath()
                    .document("${x++}")
                    .set(hashMap)
                    .addOnCompleteListener { Log.d(TAG, "Add Complete ") }
                    .addOnCanceledListener { Log.d(TAG, "Add Canceled ") }
                    .addOnSuccessListener { Log.d(TAG, "Add added with ID: ${it}") }
                    .addOnFailureListener { Log.d(TAG, "Add failure ERROR $it") }
        }
    }

    fun addRecord2(collectionName: String) {
        var hashMap = HashMap<String, Any>()
        hashMap.let { hashMap ->
            hashMap["Name"] = "CCCCC"
            hashMap["Country"] = "USA"
            hashMap["Timestamp"] = System.currentTimeMillis()
            Log.d(TAG, "add Record2......")
            getDBGuestPath()
                    .document("${y++}")
                    .set(hashMap)
                    .addOnCompleteListener { Log.d(TAG, "Add Complete ") }
                    .addOnCanceledListener { Log.d(TAG, "Add Canceled ") }
                    .addOnSuccessListener { Log.d(TAG, "Add added with ID: ${it}") }
                    .addOnFailureListener { Log.d(TAG, "Add failure ERROR $it") }
        }
    }


    fun readRecord(collectionName: String) {
        Log.d(TAG, "read Record")
        firestore.collection(collectionName)
                .get()
                .addOnCompleteListener {
                    Log.d(TAG, "Get Complete ")
//                    for (document in it.getResult()) {
//                        Log.d(TAG, "Complete " + document.getId() + " => " + document.getData())
//                    }
                }
                .addOnCanceledListener { Log.d(TAG, "Get Canceled ") }
                .addOnSuccessListener { Log.d(TAG, "Get Success Size: ${it.size()}  DOCUMENTS: ${it.documents}") }
                .addOnFailureListener { Log.d(TAG, "Get Failure ERROR $it") }
    }


}
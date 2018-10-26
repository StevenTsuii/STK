package com.example.steven.stk.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.R
import com.example.steven.stk.base.activity.BaseFragment
import com.example.steven.stk.extension.plugFragmentComponent
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_my_cloud.*
import java.util.*
import javax.inject.Inject


class MyCloudFragment : BaseFragment() {

    private val TAG = this.javaClass.simpleName
    private val RECORD_A = "recordaa"
    private val RECORD_B = "recordbb"
    @Inject
    lateinit var firestore: FirebaseFirestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_my_cloud, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plugFragmentComponent().inject(this)

        add_record_a_1.setOnClickListener { addRecord1(RECORD_A) }
        add_record_a_2.setOnClickListener { addRecord2(RECORD_A) }

        add_record_b_1.setOnClickListener { addRecord1(RECORD_B) }
        add_record_b_2.setOnClickListener { addRecord2(RECORD_B) }

        read_record_a.setOnClickListener { readRecord(RECORD_A) }
        read_record_b.setOnClickListener { readRecord(RECORD_B) }
    }


    fun addRecord1(collectionName: String) {
        var hashMap = HashMap<String, Any>()
        hashMap.let { hashMap ->
            hashMap["Name"] = "Steven"
            hashMap["Timestamp"] = System.currentTimeMillis()

            firestore
                    .collection(collectionName)
                    .add(hashMap)
                    .addOnCompleteListener { Log.d(TAG, "Add Complete ") }
                    .addOnCanceledListener { Log.d(TAG, "Add Canceled ") }
                    .addOnSuccessListener { Log.d(TAG, "Add added with ID: ${it.id}") }
                    .addOnFailureListener { Log.d(TAG, "Add failure ERROR $it") }
        }
    }

    fun addRecord2(collectionName: String) {
        var hashMap = HashMap<String, Any>()
        hashMap.let { hashMap ->
            hashMap["Name"] = "Carol"
            hashMap["Timestamp"] = System.currentTimeMillis()

            firestore
                    .collection(collectionName)
                    .add(hashMap)
                    .addOnCompleteListener { Log.d(TAG, "Add Complete ") }
                    .addOnCanceledListener { Log.d(TAG, "Add Canceled ") }
                    .addOnSuccessListener { Log.d(TAG, "Add added with ID: ${it.id}") }
                    .addOnFailureListener { Log.d(TAG, "Add failure ERROR $it") }
        }
    }


    fun readRecord(collectionName: String) {
        firestore.collection(collectionName)
                .get()
                .addOnCompleteListener {
                    Log.d(TAG, "Get Complete ")
                    for (document in it.getResult()) {
                        Log.d(TAG, "Complete " + document.getId() + " => " + document.getData())
                    }
                }
                .addOnCanceledListener { Log.d(TAG, "Get Canceled ") }
                .addOnSuccessListener { Log.d(TAG, "Get Success Size: ${it.size()}  DOCUMENTS: ${it.documents}") }
                .addOnFailureListener { Log.d(TAG, "Get Failure ERROR $it") }
    }


}
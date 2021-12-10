package com.app.postmaker.ui.fragment

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.app.postmaker.R
import com.app.postmaker.database.DatabaseClient
import com.app.postmaker.item.ProfileList
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class ProfileFragment : Fragment() {

    private lateinit var button: MaterialButton
    private lateinit var editTextName: TextInputEditText
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPhoneNO: TextInputEditText
    private lateinit var editTextWebSite: TextInputEditText
    private lateinit var editTextAddress: TextInputEditText

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.profile_fragment, container, false)

        button = view.findViewById(R.id.button_profile)
        editTextName = view.findViewById(R.id.editText_name_pro)
        editTextEmail = view.findViewById(R.id.editText_email_pro)
        editTextPhoneNO = view.findViewById(R.id.editText_phone_pro)
        editTextWebSite = view.findViewById(R.id.editText_webSite_pro)
        editTextAddress = view.findViewById(R.id.editText_address_pro)

        button.setOnClickListener { v ->
            AsyncTask.execute {
                activity?.let {
                    DatabaseClient.getInstance(it)?.appDatabase
                        ?.userTask()
                        ?.insert(
                            ProfileList(
                                editTextName.toString(),
                                editTextEmail.toString(),
                                editTextPhoneNO.toString(),
                                editTextWebSite.toString(),
                                editTextAddress.toString()
                            )
                        )
                }
            }
            Toast.makeText(activity, "Submit", Toast.LENGTH_SHORT).show()
        }

        return view

    }

}
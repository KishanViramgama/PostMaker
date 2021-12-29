package com.app.postmaker.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.app.postmaker.R
import com.app.postmaker.database.DatabaseClient
import com.app.postmaker.item.Profile
import com.app.postmaker.util.Method
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class ProfileFragment : Fragment() {

    private lateinit var method: Method
    private lateinit var inputMethodManager: InputMethodManager

    private lateinit var button: MaterialButton
    private lateinit var editTextName: TextInputEditText
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPhoneNo: TextInputEditText
    private lateinit var editTextWebSite: TextInputEditText
    private lateinit var editTextAddress: TextInputEditText

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.profile_fragment, container, false)

        method = activity?.let { Method(it) }!!

        inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        button = view.findViewById(R.id.button_profile)
        editTextName = view.findViewById(R.id.editText_name_pro)
        editTextEmail = view.findViewById(R.id.editText_email_pro)
        editTextPhoneNo = view.findViewById(R.id.editText_phone_pro)
        editTextWebSite = view.findViewById(R.id.editText_webSite_pro)
        editTextAddress = view.findViewById(R.id.editText_address_pro)

        button.setOnClickListener {

            val name: String = editTextName.text.toString()
            val email: String = editTextEmail.text.toString()
            val phoneNo: String = editTextPhoneNo.text.toString()
            val website: String = editTextWebSite.text.toString()
            val address: String = editTextAddress.text.toString()

            editTextName.error = null
            editTextEmail.error = null
            editTextPhoneNo.error = null
            editTextWebSite.error = null
            editTextAddress.error = null

            if (name == "" || name.isEmpty()) {
                editTextName.requestFocus()
                editTextName.error = resources.getString(R.string.please_enter_name)
            } else if (!isValidMail(email)) {
                editTextEmail.requestFocus()
                editTextEmail.error = resources.getString(R.string.please_enter_email)
            } else if (phoneNo == "" || phoneNo.isEmpty()) {
                editTextPhoneNo.requestFocus()
                editTextPhoneNo.error = resources.getString(R.string.please_enter_phoneNo)
            } else if (website == "" || website.isEmpty()) {
                editTextWebSite.requestFocus()
                editTextWebSite.error = resources.getString(R.string.please_enter_web)
            } else if (address == "" || address.isEmpty()) {
                editTextAddress.requestFocus()
                editTextAddress.error = resources.getString(R.string.please_enter_address)
            } else {

                editTextName.clearFocus()
                editTextEmail.clearFocus()
                editTextPhoneNo.clearFocus()
                editTextWebSite.clearFocus()
                editTextAddress.clearFocus()
                inputMethodManager.hideSoftInputFromWindow(editTextName.windowToken, 0)
                inputMethodManager.hideSoftInputFromWindow(editTextEmail.windowToken, 0)
                inputMethodManager.hideSoftInputFromWindow(editTextPhoneNo.windowToken, 0)
                inputMethodManager.hideSoftInputFromWindow(editTextWebSite.windowToken, 0)
                inputMethodManager.hideSoftInputFromWindow(editTextAddress.windowToken, 0)

                activity?.let {
                    if (DatabaseClient.getInstance(it)?.appDatabase?.userTask()!!.isRowIsExist(1)) {
                        DatabaseClient.getInstance(it)?.appDatabase
                            ?.userTask()
                            ?.update(
                                Profile(
                                    name, email, phoneNo, website, address, 1
                                )
                            )
                        method.alertBox(resources.getString(R.string.your_data_update))
                    } else {
                        DatabaseClient.getInstance(it)?.appDatabase
                            ?.userTask()
                            ?.insert(
                                Profile(
                                    name, email, phoneNo, website, address, 1
                                )
                            )
                        method.alertBox(resources.getString(R.string.your_data_insert))
                    }

                    editTextName.setText("")
                    editTextEmail.setText("")
                    editTextPhoneNo.setText("")
                    editTextWebSite.setText("")
                    editTextAddress.setText("")

                }

            }
        }

        return view

    }

    private fun isValidMail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}
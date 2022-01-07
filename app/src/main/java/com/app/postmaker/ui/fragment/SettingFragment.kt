package com.app.postmaker.ui.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.app.postmaker.R
import com.app.postmaker.ui.activity.AboutUs
import com.app.postmaker.ui.activity.PrivacyPolicy
import com.app.postmaker.ui.activity.Splash
import com.app.postmaker.util.Method
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.textview.MaterialTextView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.text.DecimalFormat


class SettingFragment : Fragment() {

    private lateinit var method: Method
    private lateinit var them: String
    private lateinit var themMode: String
    private lateinit var textViewCash: MaterialTextView

    @SuppressLint("SetTextI18n", "NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.setting_fragment, container, false)

        method = activity?.let { Method(it) }!!

        textViewCash = view.findViewById(R.id.textView_cash_setting)
        val textViewThemName: MaterialTextView = view.findViewById(R.id.textView_themName_setting)
        val textViewShareApp: MaterialTextView = view.findViewById(R.id.textView_shareApp_setting)
        val textViewRateApp: MaterialTextView = view.findViewById(R.id.textView_rateApp_setting)
        val textViewMoreApp: MaterialTextView = view.findViewById(R.id.textView_moreApp_setting)
        val textViewAboutUs: MaterialTextView = view.findViewById(R.id.textView_aboutUs_setting)
        val textViewPrivacyPolicy: MaterialTextView =
            view.findViewById(R.id.textView_privacy_setting)
        val imageViewClear: ImageView = view.findViewById(R.id.imageView_clear_setting)
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.con_setting)

        them = method.pref.getString(method.themSetting, "system").toString()
        when (them) {
            "system" -> textViewThemName.text = resources.getString(R.string.system_default)
            "light" -> textViewThemName.text = resources.getString(R.string.light)
            "dark" -> textViewThemName.text = resources.getString(R.string.dark)
        }

        textViewCash.text = (resources.getString(R.string.cash_file) + " " + DecimalFormat("##.##")
            .format(
                (FileUtils.sizeOfDirectory(requireActivity().cacheDir) + FileUtils.sizeOfDirectory(
                    File(
                        requireActivity().externalCacheDir!!.absolutePath
                    )
                )) / (1024 * 1024)
            )
                + " " + resources.getString(R.string.mb))

        imageViewClear.setOnClickListener { v: View? ->
            FileUtils.deleteQuietly(requireActivity().cacheDir)
            try {
                FileUtils.deleteDirectory(
                    File(
                        requireActivity().externalCacheDir!!.absolutePath
                    )
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
            textViewCash.text =
                resources.getString(R.string.cash_file) + " " + FileUtils.sizeOfDirectory(
                    requireActivity().cacheDir
                ) + " " + resources.getString(
                    R.string.mb
                )
        }

        constraintLayout.setOnClickListener {
            val dialog = Dialog(requireActivity())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_them)
            dialog.window?.setLayout(
                ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT
            )
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            val radioGroup: RadioGroup = dialog.findViewById(R.id.radioGroup_them)!!
            val textViewOk: MaterialTextView = dialog.findViewById(R.id.textView_ok_them)
            val textViewCancel: MaterialTextView = dialog.findViewById(R.id.textView_cancel_them)
            when (them) {
                "system" -> radioGroup.check(radioGroup.getChildAt(0).id)
                "light" -> radioGroup.check(radioGroup.getChildAt(1).id)
                "dark" -> radioGroup.check(radioGroup.getChildAt(2).id)
            }
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val rb: MaterialRadioButton = group.findViewById(checkedId)
                if (checkedId > -1) {
                    when (checkedId) {
                        R.id.radioButton_system_them -> themMode = "system"
                        R.id.radioButton_light_them -> themMode = "light"
                        R.id.radioButton_dark_them -> themMode = "dark"
                    }
                }
            }
            textViewOk.setOnClickListener {
                method.editor.putString(method.themSetting, themMode)
                method.editor.commit()
                dialog.dismiss()
                startActivity(Intent(requireActivity(), Splash::class.java))
                requireActivity().finishAffinity()
            }
            textViewCancel.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }

        textViewShareApp.setOnClickListener { shareApp() }

        textViewRateApp.setOnClickListener { rateApp() }

        textViewMoreApp.setOnClickListener { moreApp() }

        textViewAboutUs.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    AboutUs::class.java
                )
            )
        }

        textViewPrivacyPolicy.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    PrivacyPolicy::class.java
                )
            )
        }


        return view
    }

    private fun shareApp() {
        try {
            val string = """
            
            ${resources.getString(R.string.Let_me_recommend_you_this_application)}
            
            https://play.google.com/store/apps/details?id=${requireActivity().application.packageName}
            """.trimIndent()
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.app_name))
            intent.putExtra(Intent.EXTRA_TEXT, string)
            startActivity(Intent.createChooser(intent, resources.getString(R.string.choose_one)))
        } catch (e: Exception) {
            e.toString()
        }
    }

    private fun rateApp() {
        val uri: Uri = Uri.parse("market://details?id=" + requireActivity().application.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        // To count with Play market back stack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + requireActivity().application.packageName)
                )
            )
        }
    }

    private fun moreApp() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(resources.getString(R.string.play_more_app))
            )
        )
    }

}
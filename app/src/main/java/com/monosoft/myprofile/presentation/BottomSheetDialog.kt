package com.monosoft.myprofile.presentation


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.monosoft.myprofile.R
import com.monosoft.myprofile.presentation.util.Technology
import com.monosoft.myprofile.presentation.viewmodels.FirebaseViewModel
import com.monosoft.myprofile.presentation.viewmodels.TelegramViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.annotation.Nullable


@AndroidEntryPoint
class BottomSheetDialog(text_:String,technology_:String,tknFrb_ : String,telegramViewModel_ : TelegramViewModel,firebaseViewModel_: FirebaseViewModel) : BottomSheetDialogFragment() {

    val technology = technology_
    val text = text_
    val telegramViewModel = telegramViewModel_
    val tknFrb = tknFrb_
    val firebaseViewModel = firebaseViewModel_

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {

        setStyle(DialogFragment.STYLE_NORMAL, R.style.TodoInputDialogFragment)

            val v: View = inflater.inflate(
            R.layout.bottom_sheet_contact_me,
            container, false
        )


        val imageView = v.findViewById<LottieAnimationView>(R.id.imageViewB)
        imageView.setAnimation(R.raw.whatsapp);
        imageView.playAnimation();

        val lblTextBottomContactMe = v.findViewById<TextView>(R.id. lblTextBottomContactMe)
        lblTextBottomContactMe.setMovementMethod(ScrollingMovementMethod())
        lblTextBottomContactMe.text = text //"Puedes enviarme un mensaje por ${technology}. Utilizando las aplicaciones de mensajeria y redes sociales instaladas en el dispositivo."

        val txtMessageBottom = v.findViewById<EditText>(R.id.txtMessageBottom)

        val algo_button = v.findViewById<Button>(R.id.btnSend)
        val course_button = v.findViewById<Button>(R.id.btnCloseBottomSheet)
        algo_button.setOnClickListener {
            when(technology.uppercase()){
               Technology.WHATSAPP.type->{
                   shareMessageWhatsApp(txtMessageBottom.text.toString())
                   dismiss()
               }
                Technology.TELEGRAM.type->{
                    telegramViewModel.sendMessageTelegram(txtMessageBottom.text.toString())
                    dismiss()
                }
                Technology.FIREBASE.type->{
                    firebaseViewModel.sendNotificationPushFirebase(txtMessageBottom.text.toString(),tknFrb,"titulo")
                    dismiss()
                }
//                Technology.FIREBASE.type->{
//                    firebaseViewModel.sendNotificationPushFirebase(txtMessageBottom.text.toString(),tknFrb,"titulo")
//                    dismiss()
//                }
            }

        }
        course_button.setOnClickListener {
            Toast.makeText(
                activity,
                "Course Shared", Toast.LENGTH_SHORT
            ).show()
            dismiss()
        }
//        val bottomSheetBehavior: BottomSheetBehavior<View> =
//            BottomSheetBehavior.from<View>(v)
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        return v
    }



    fun shareMessageWhatsApp(message :String){

        var phone ="8110065390"
        try {
            var mPhoneNumber = "+528110065390"
            mPhoneNumber = mPhoneNumber.replace("/+".toRegex(), "").replace(" ".toRegex(), "")
                .replace("-".toRegex(), "")
            val mMessage = message
            val mSendToWhatsApp = "https://wa.me/$mPhoneNumber?text=$mMessage"
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        mSendToWhatsApp
                    )
                )
            )
//            val uri = Uri.parse("smsto:$phone")
//            val i = Intent(Intent.ACTION_SENDTO, uri)
//            i.putExtra(Intent.EXTRA_TEXT, message)
//            i.putExtra(Intent.EXTRA_SUBJECT, "Visita generada SYGER de acceso residencial")
//            i.setPackage("com.whatsapp")
//            startActivity(i)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
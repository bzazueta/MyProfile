package com.monosoft.myprofile.presentation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.body
import com.example.awesomedialog.onNegative
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.position
import com.example.awesomedialog.title
import com.monosoft.myprofile.R
import com.monosoft.myprofile.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    lateinit var bindind : ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        bindind.imageView.setOnClickListener {
            finish()
        }

        bindind.lblPermisos.setOnClickListener {

            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            with(intent) {
                data = Uri.fromParts("package", packageName, null)
                addCategory(Intent.CATEGORY_DEFAULT)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)

            }
            startActivity(intent)
        }

        bindind.imagePermisos.setOnClickListener {

            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            with(intent) {
                data = Uri.fromParts("package", packageName, null)
                addCategory(Intent.CATEGORY_DEFAULT)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)

            }
            startActivity(intent)
        }

        bindind.imageSalir.setOnClickListener{
            /*** mostramos el mensaje despues de cargar la foto **/
            AwesomeDialog.build(this@SettingActivity)
                .title("Mensaje")
                .body("")
                .body("¿DESEAS CERRAR SESION?")
                .position(AwesomeDialog.POSITIONS.CENTER)
                .onPositive("ACEPTAR") {
                    Log.d("TAG", "positive ")
                    finishAffinity()
                }
                .onNegative("CANCELAR"){

                }

        }
    }
}
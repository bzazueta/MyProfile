package com.monosoft.myprofile.presentation.screens.technology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.monosoft.myprofile.R
import com.monosoft.myprofile.databinding.ActivityAndroidBinding
import com.monosoft.myprofile.databinding.ActivityTechnologyBinding
import com.monosoft.myprofile.presentation.screens.apps.AppsActivity
import com.monosoft.myprofile.presentation.util.Constant
import com.monosoft.myprofile.presentation.util.Technology

class AndroidActivity : AppCompatActivity() {

    lateinit var bindin : ActivityAndroidBinding
    var text1 =  ""
    var text2 =  ""
    var text3=   ""
    var text4 =  ""
    var text5 =  ""
    var text6 =  ""
    var text7 =  ""
    var image =  ""
    var technology =  ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindin = ActivityAndroidBinding.inflate(layoutInflater)
        setContentView(bindin.root)

        text1 = intent.getStringExtra("text1").toString() ?: ""
        text2 = intent.getStringExtra("text2").toString() ?: ""
        text3= intent.getStringExtra("text3").toString() ?: ""
        text4 = intent.getStringExtra("text4").toString() ?: ""
        text5 = intent.getStringExtra("text5").toString() ?: ""
        text6 = intent.getStringExtra("text6").toString() ?: ""
        text7 = intent.getStringExtra("text7").toString() ?: ""
        image = intent.getStringExtra("image").toString() ?: ""
        technology = intent.getStringExtra("technology").toString() ?: ""

        bindin.lblTechnologyT.text = technology
        Glide.with(this@AndroidActivity).load(image).into(bindin.imageTech)
        setUp()

        bindin.lblVerT.setOnClickListener {

            when(technology.uppercase()){

                Technology.FIREBASE.type->{

                }
                Technology.GOOGLEMAPS.type->{
                    bindin.lblInfoT.visibility= View.VISIBLE
                    bindin.lblVerT.visibility= View.VISIBLE
                    bindin.lblInfoT.setText(getString(R.string.text_map))
                }
                Technology.API.type->{

                }
                Technology.ANDROID.type->{
                    val intent = Intent(this@AndroidActivity, AppsActivity::class.java)
                    intent.putExtra("technology", Technology.ANDROID.type)
                    startActivity(intent)
                }
                Technology.FLUTTER.type->{
                    val intent = Intent(this@AndroidActivity, AppsActivity::class.java)
                    intent.putExtra("technology", Technology.FLUTTER.type)
                    startActivity(intent)
                }
            }

        }

        bindin.cardEcommerce.setOnClickListener {
            val intent = Intent(this@AndroidActivity, AppsActivity::class.java)
            intent.putExtra("technology", Technology.ANDROID.type)
            intent.putExtra("nameApp", Constant.ECOMMERCE)
            startActivity(intent)
        }

        bindin.cardMyAcceso.setOnClickListener {
            val intent = Intent(this@AndroidActivity, AppsActivity::class.java)
            intent.putExtra("technology", Technology.ANDROID.type)
            intent.putExtra("nameApp", Constant.MYACCESO)
            startActivity(intent)
        }

        bindin.imageView6.setOnClickListener {
            finish()
        }

    }

    fun setUp(){
        if(!text1.equals("") && !text1.equals("null")){
            bindin.lblTextT.visibility= View.VISIBLE
            bindin.lblTextT.text = text1
        }
        if(!text2.equals("") && !text2.equals("null")){
            bindin.lblTextT2.visibility= View.VISIBLE
            bindin.lblTextT2.text = text2
        }
        if(!text3.equals("") && !text3.equals("null")){
            bindin.lblTextT3.visibility= View.VISIBLE
            bindin.lblTextT3.text = text3
        }
        if(!text4.equals("") && !text4.equals("null")){
            bindin.lblTextT4.visibility= View.VISIBLE
            bindin.lblTextT4.text = text4
        }
        if(!text5.equals("") && !text5.equals("null")){
            bindin.lblTextT5.visibility= View.VISIBLE
            bindin.lblTextT5.text = text5
        }
        if(!text6.equals("") && !text6.equals("null")){
            bindin.lblTextT6.visibility= View.VISIBLE
            bindin.lblTextT6.text = text6
        }
        if(!text7.equals("") && !text7.equals("null")){
            bindin.lblTextT7.visibility= View.VISIBLE
            bindin.lblTextT7.text = text7
        }

        when(technology.uppercase()){

            Technology.FIREBASE.type->{

            }
            Technology.GOOGLEMAPS.type->{
                bindin.lblInfoT.visibility= View.VISIBLE
                bindin.lblVerT.visibility= View.VISIBLE
                bindin.lblInfoT.setText(getString(R.string.text_map))
            }
            Technology.API.type->{

            }
            Technology.ANDROID.type->{
                bindin.lblInfoT.visibility= View.VISIBLE
                bindin.lblVerT.visibility= View.VISIBLE
                bindin.lblVerT.text= "Ver Apps"
                bindin.lblInfoT.setTextColor(getColor(R.color.green400))
                bindin.lblInfoT.setText(getString(R.string.text_flutter))
                bindin.lblInfoT.setBackgroundResource(R.drawable.bordes_textview_green)

            }
            Technology.FLUTTER.type->{
                bindin.lblInfoT.visibility= View.VISIBLE
                bindin.lblVerT.visibility= View.VISIBLE
                bindin.lblVerT.text= "Ver Apps"
                bindin.lblInfoT.setText(getString(R.string.text_flutter))
            }
        }
    }
}
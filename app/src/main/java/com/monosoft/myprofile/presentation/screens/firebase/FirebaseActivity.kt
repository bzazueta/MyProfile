package com.monosoft.myprofile.presentation.screens.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.monosoft.myprofile.R
import com.monosoft.myprofile.databinding.ActivityAndroidBinding
import com.monosoft.myprofile.databinding.ActivityFirebaseBinding
import com.monosoft.myprofile.presentation.screens.apps.AppsActivity
import com.monosoft.myprofile.presentation.util.Technology

class FirebaseActivity : AppCompatActivity() {

    lateinit var binding : ActivityFirebaseBinding
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
        binding = ActivityFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        text1 = intent.getStringExtra("text1").toString() ?: ""
        text2 = intent.getStringExtra("text2").toString() ?: ""
        text3= intent.getStringExtra("text3").toString() ?: ""
        text4 = intent.getStringExtra("text4").toString() ?: ""
        text5 = intent.getStringExtra("text5").toString() ?: ""
        text6 = intent.getStringExtra("text6").toString() ?: ""
        text7 = intent.getStringExtra("text7").toString() ?: ""
        image = intent.getStringExtra("image").toString() ?: ""
        technology = intent.getStringExtra("technology").toString() ?: ""

        binding.lblTechnologyT.text = technology
        Glide.with(this@FirebaseActivity).load(image).into(binding.imageTech)
        setUp()

        binding.lblVerT.setOnClickListener {

            when(technology.uppercase()){

                Technology.FIREBASE.type->{

                }
                Technology.GOOGLEMAPS.type->{
                    binding.lblInfoT.visibility= View.VISIBLE
                    binding.lblVerT.visibility= View.VISIBLE
                    binding.lblInfoT.setText(getString(R.string.text_map))
                }
                Technology.API.type->{

                }
                Technology.ANDROID.type->{
                    val intent = Intent(this@FirebaseActivity, AppsActivity::class.java)
                    intent.putExtra("technology", Technology.ANDROID.type)
                    startActivity(intent)
                }
                Technology.FLUTTER.type->{
                    val intent = Intent(this@FirebaseActivity, AppsActivity::class.java)
                    intent.putExtra("technology", Technology.FLUTTER.type)
                    startActivity(intent)
                }
            }

        }

        binding.imageView6.setOnClickListener {
            finish()
        }
    }

    fun setUp(){
        if(!text1.equals("") && !text1.equals("null")){
            binding.lblTextT.visibility= View.VISIBLE
            binding.lblTextT.text = text1
        }
        if(!text2.equals("") && !text2.equals("null")){
            binding.lblTextT2.visibility= View.VISIBLE
            binding.lblTextT2.text = text2
        }
        if(!text3.equals("") && !text3.equals("null")){
            binding.lblTextT3.visibility= View.VISIBLE
            binding.lblTextT3.text = text3
        }
        if(!text4.equals("") && !text4.equals("null")){
            binding.lblTextT4.visibility= View.VISIBLE
            binding.lblTextT4.text = text4
        }
        if(!text5.equals("") && !text5.equals("null")){
            binding.lblTextT5.visibility= View.VISIBLE
            binding.lblTextT5.text = text5
        }
        if(!text6.equals("") && !text6.equals("null")){
            binding.lblTextT6.visibility= View.VISIBLE
            binding.lblTextT6.text = text6
        }
        if(!text7.equals("") && !text7.equals("null")){
            binding.lblTextT7.visibility= View.VISIBLE
            binding.lblTextT7.text = text7
        }

        binding.lblInfoT.setText(getString(com.monosoft.myprofile.R.string.text_flutter))


    }
}
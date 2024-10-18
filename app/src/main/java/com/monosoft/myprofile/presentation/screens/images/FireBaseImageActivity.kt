package com.monosoft.myprofile.presentation.screens.images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.monosoft.myprofile.R
import com.monosoft.myprofile.databinding.ActivityFireBaseImageBinding

class FireBaseImageActivity : AppCompatActivity() {

    lateinit var binding : ActivityFireBaseImageBinding
    var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireBaseImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        url = intent.getStringExtra("url").toString() ?: ""

        if(!url.equals(null) || !url.equals(null)){
            Glide.with(this@FireBaseImageActivity).load(url)
                .into(binding.imageViewFirebaseStorage);
        }

        binding.imageView.setOnClickListener {
            finish()
        }

    }
}
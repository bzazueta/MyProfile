package com.monosoft.myprofile.presentation.screens.apps

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.monosoft.myprofile.R
import com.monosoft.myprofile.databinding.ActivityAppsBinding
import com.monosoft.myprofile.presentation.adapters.ImageAdapter
import com.monosoft.myprofile.presentation.util.Technology
import kotlin.math.abs


class AppsActivity : AppCompatActivity() {

    lateinit var binding : ActivityAppsBinding
    private lateinit var  viewPager2: ViewPager2
    private lateinit var handler : Handler
    private lateinit var imageList:ArrayList<Int>
    private lateinit var textList:ArrayList<String>
    private lateinit var adapter: ImageAdapter
    var technology = ""
    var app = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        technology = intent.getStringExtra("technology").toString() ?: ""
        app = intent.getStringExtra("nameApp").toString() ?: ""
        init()
        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable , 2000)
            }
        })

        binding.imageView6.setOnClickListener {
            finish()
        }

        binding.buttonAppStore.setOnClickListener {
            if(app.equals("MYACCESO")) {
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://apps.apple.com/us/app/myacceso/id1668941734")
                )
                startActivity(urlIntent)
            }
        }

        binding.buttonPlayStore.setOnClickListener {
            if(app.equals("ECOMMERCE"))
            {

            }
            if(app.equals("MYACCESO"))
            {
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.monosoft.myacceso")
                )
                startActivity(urlIntent)
            }

        }
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable , 2000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f

        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewPager2 = findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()
        textList = ArrayList()

        when(technology.uppercase()){


            Technology.ANDROID.type->{
                if(app.equals("ECOMMERCE"))
                {
                    binding.buttonPlayStore.visibility = View.GONE
                    binding.buttonAppStore.visibility = View.GONE
                    binding.buttonGitHub.visibility = View.GONE
                    imageList.add(R.drawable.ecommerce1)
                    imageList.add(R.drawable.ecommerce2)
                    imageList.add(R.drawable.ecommerce3)
                    imageList.add(R.drawable.ecommerce4)

                    textList.add("Ecommerce App")
                    textList.add("Roles")
                    textList.add("Productos")
                    textList.add("Categorias")
                }
                if(app.equals("MYACCESO"))
                {
                    imageList.add(R.drawable.myacceso1)
                    imageList.add(R.drawable.myacceso2)
                    imageList.add(R.drawable.myacceso3)
                    imageList.add(R.drawable.myacceso4)

                    textList.add("Myacceso App")
                    textList.add("Dashboard Informativo")
                    textList.add("Funciones")
                    textList.add("Creación de QR")
                }


            }

            Technology.FLUTTER.type->{
                binding.buttonPlayStore.visibility = View.GONE
                binding.buttonAppStore.visibility = View.VISIBLE
                imageList.add(R.drawable.inicio)
                imageList.add(R.drawable.login)
                imageList.add(R.drawable.dashboard)
                imageList.add(R.drawable.qr)
                imageList.add(R.drawable.qrdetalle)
                imageList.add(R.drawable.codigoverificado)
                imageList.add(R.drawable.usuarios)
                imageList.add(R.drawable.crearusuario)
                imageList.add(R.drawable.usuariodetalle)

                textList.add("Myacceso App")
                textList.add("Login")
                textList.add("Dashboard Informativo")
                textList.add("Funciones")
                textList.add("Creación de QR")
                textList.add("Código verificado")
                textList.add("Usuarios")
                textList.add("Crear usuarios")
                textList.add("Usuarios detalles")
            }
        }





        adapter = ImageAdapter(imageList, viewPager2,textList)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }
}
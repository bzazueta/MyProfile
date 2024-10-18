package com.monosoft.myprofile.presentation.screens.api

import android.R
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.monosoft.myprofile.databinding.ActivityApiBinding
import com.monosoft.myprofile.domain.models.CrudApiModel
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.utils.Resource
import com.monosoft.myprofile.presentation.adapters.ApiAdapter
import com.monosoft.myprofile.presentation.viewmodels.CrudApiViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ApiActivity : AppCompatActivity() {

    lateinit var binding : ActivityApiBinding
    var apiAdapter : ApiAdapter? = null
    var apiCrudLIst : List<CrudApiModel> = listOf()
    val apiCrudViewModel : CrudApiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val text1 = intent.getStringExtra("text1").toString() ?: ""
        val text2 = intent.getStringExtra("text2").toString() ?: ""
        val text3= intent.getStringExtra("text3").toString() ?: ""
        val text4 = intent.getStringExtra("text4").toString() ?: ""
        val text5 = intent.getStringExtra("text5").toString() ?: ""
        val text6 = intent.getStringExtra("text6").toString() ?: ""
        val text7 = intent.getStringExtra("text7").toString() ?: ""
        val image = intent.getStringExtra("image").toString() ?: ""
        val technology = intent.getStringExtra("technology").toString() ?: ""

        binding.lblTechnologyApi.text = technology
        Glide.with(this@ApiActivity).load(image).into(binding.imageApi)
        if(!text1.equals("") && !text1.equals("null")){
            binding.lblTextApi.visibility= View.VISIBLE
            binding.lblTextApi.text = text1
        }
        if(!text2.equals("") && !text2.equals("null")){
            binding.lblTextApi2.visibility= View.VISIBLE
            binding.lblTextApi2.text = text2
        }

        binding.imageViewCloseApi.setOnClickListener {
            finish()
        }

        apiCrudViewModel.getAllApi()

        val mLayoutManager2: RecyclerView.LayoutManager = LinearLayoutManager(
            this@ApiActivity, LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerApi.layoutManager = mLayoutManager2
        binding.recyclerApi.itemAnimator = DefaultItemAnimator()
        binding.recyclerApi.setLayoutManager(mLayoutManager2)
        binding.recyclerApi.setHasFixedSize(true)


        binding.lblAgregarApi.setOnClickListener{
            showAlertDialogButtonClicked()
        }

        binding.lblGitNodeJsApi.setOnClickListener {

            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/bzazueta/ecommerce-backend-benja")
            )
            startActivity(urlIntent)
        }

        binding.lblGitPhpApi.setOnClickListener {

        }

        lifecycleScope.launch {
            apiCrudViewModel.getAllApiResponse.collect{
                when(it){
                    Resource.Loading->{loadingState()}
                    is Resource.Success->{
                        it.data.apiCRUD
                        apiAdapter = ApiAdapter(this@ApiActivity,it.data.apiCRUD,apiCrudViewModel)
                        binding.recyclerApi.adapter = apiAdapter
                        apiAdapter!!.notifyDataSetChanged()
                    }
                    is Resource.Failure->{ closeLoadingState() }

                    else -> {}
                }

            }
        }

        lifecycleScope.launch {
            apiCrudViewModel.addNameApiResponse.collect{
                  when(it){
                     Resource.Loading->{loadingState()}
                     is Resource.Success->{
                         it.data.apiCRUD
                         apiAdapter = ApiAdapter(this@ApiActivity,it.data.apiCRUD,apiCrudViewModel)
                         binding.recyclerApi.adapter = apiAdapter
                         apiAdapter!!.notifyDataSetChanged()
                     }
                     is Resource.Failure->{ closeLoadingState() }

                      else -> {}
                  }

            }
        }

        lifecycleScope.launch {
            apiCrudViewModel.deleteApiResponse.collect{
                when(it){
                    Resource.Loading->{loadingState()}
                    is Resource.Success->{
                        if(it.data.response.equals("Ok")){
                            showSnackBar(it.data.msg)
                        }else{
                            showSnackBar(it.data.msg)
                        }
                    }
                    is Resource.Failure->{ closeLoadingState() }

                    else -> {}
                }

            }
        }
    }

    @SuppressLint("MissingInflatedId")
    fun showAlertDialogButtonClicked() {
        // Create an alert builder
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        //builder.setTitle("Name")

        // set the custom layout
        val customLayout: View = layoutInflater.inflate(com.monosoft.myprofile.R.layout.api_dialog, null)
        builder.setView(customLayout)
        val editText = customLayout.findViewById<EditText>(com.monosoft.myprofile.R.id.txtApiName)
        val buton = customLayout.findViewById<Button>(com.monosoft.myprofile.R.id.btnAdd)
        val dialog: AlertDialog = builder.create()

        buton.setOnClickListener{
            dialog.dismiss()
            val editText = customLayout.findViewById<EditText>(com.monosoft.myprofile.R.id.txtApiName)
            apiCrudViewModel.addNameApi(editText.text.toString(),"")

        }
        // create and show the alert dialog

        dialog.show()
    }

    fun showSnackBar(msg: String) {
        Snackbar.make(binding.constraint, msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun loadingState() {
        //binding.pb.isVisible = true
    }

    private fun closeLoadingState() {
        //binding.pb.isVisible = false
    }

}